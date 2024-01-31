package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.TransacSessionDao;
import com.flexmoney.projectfm.model.Lender;
import com.flexmoney.projectfm.model.TransacSession;
import com.flexmoney.projectfm.model.Transaction;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class TransacSessionDaoImpl implements TransacSessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PaUserLenderDao paUserLenderDao;

    @Override
    public String initSession(Integer mobile, BigDecimal amount) {
        UUID sessionId = UUID.randomUUID();
        return jdbcTemplate.update("INSERT INTO transac_session (session_id, mobile, amount) VALUES (?, ?, ?)",
                sessionId, mobile, amount)
        +" created Successfully ";
    }

    @Override
    public String updateSession(UUID session_id, UUID transac_id) {
        int numRowsUpdated = jdbcTemplate.update(
                "UPDATE transac_session " +
                        "SET transac_id = ?" +
                        "WHERE session_id = ?",
                transac_id, session_id);

        if (numRowsUpdated > 0) {
            return "Updated successfully";
        } else {
            return "No rows updated";
        }
    }

    @Override
    public boolean completeTwoFa(Integer mobile, Integer lender_id, String two_fa_value) {
        String actual2Fa=paUserLenderDao.getTwoFaValue(mobile, lender_id);
        return two_fa_value==actual2Fa;
    }

    @Override
    public boolean setTransaction(UUID session_id, UUID lender_id, Integer tenure, BigDecimal interest_rate) {
        UUID transaction_id = UUID.randomUUID();

        try {
            // Fetch the TransacSession for the given session_id
            TransacSession transacSession = jdbcTemplate.queryForObject(
                    "SELECT * FROM transac_session WHERE session_id = ?",
                    new BeanPropertyRowMapper<>(TransacSession.class),
                    session_id
            );

            // Update the transac_id in the transac_session table
            jdbcTemplate.update(
                    "UPDATE transac_session SET transac_id = ? WHERE session_id = ?",
                    transaction_id, session_id
            );

            // Insert a new transaction record
            assert transacSession != null;
            jdbcTemplate.update(
                    "INSERT INTO transactions VALUES (?, ?, ?, CURDATE(), ?, ?, ?, 'Ongoing', CURTIME())",
                    transaction_id,
                    transacSession.getMobile(),
                    transacSession.getTotalAmount(),
                    lender_id,
                    tenure,
                    interest_rate
            );

            // If both updates are successful, return true
            return true;
        } catch (Exception e) {
            // Handle exceptions here (e.g., log the error)
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean otpVerification(Integer otp, UUID session_id) {
        UUID transaction_id = jdbcTemplate.queryForObject(
                "SELECT transac_id FROM transac_session WHERE session_id = ?",
                UUID.class,
                session_id
        );

        //update the code later to allow 3 retries.
        if(otp!=1234) {
            jdbcTemplate.update(
                    "UPDATE transationcs " +
                            "SET status=failed " +
                            "WHERE transac_id=?",
                    transaction_id
            );
            return false;
        }


        jdbcTemplate.update(
                "UPDATE transationcs " +
                        "SET status=completed " +
                        "WHERE transac_id=?",
                transaction_id
        );
        return true;
    }

    @Override
    public String sendSMS(UUID session_id) {
        UUID transaction_id = jdbcTemplate.queryForObject(
                "SELECT transac_id FROM transac_session WHERE session_id = ?",
                UUID.class,
                session_id
        );
        Transaction transaction = jdbcTemplate.queryForObject(
                "SELECT * FROM transactions WHERE transac_id = ?",
                new BeanPropertyRowMapper<>(Transaction.class),
                session_id
        );
        User user=jdbcTemplate.queryForObject(
                "SELECT * FROM users " +
                        "WHERE mobile=?",
                new BeanPropertyRowMapper<>(User.class),
                transaction.getMobile()
        );
        String sms="Hi "+user.getFirst_name()+" "+user.getLast_name()+
                ", Your payment of INR "+transaction.getTotal_amount() +
                "has been successful!\n";
        return sms;
    }

}
