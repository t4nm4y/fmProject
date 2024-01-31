package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.TransactionDao;
import com.flexmoney.projectfm.model.Session;
import com.flexmoney.projectfm.model.Transaction;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PaUserLenderDao paUserLenderDao;

    @Override
    public boolean setTransaction(UUID transaction_id, UUID lender_id, Integer tenure, BigDecimal interest_rate, Integer mobile, BigDecimal amount) {
        try {
            MapSqlParameterSource params= new MapSqlParameterSource();
            params.addValue("mobile", mobile);
            params.addValue("transaction_id", transaction_id);
            params.addValue("amount", amount);
            params.addValue("lender_id", lender_id);
            params.addValue("tenure", tenure);
            params.addValue("interest_rate", interest_rate);

            namedParameterJdbcTemplate.update(
                    "INSERT INTO transactions " +
                            "VALUES (:transaction_id, :mobile, :amount, CURDATE(), :lender_id, :tenure, :interest_rate, 'Ongoing', CURTIME())",
                    params
            );
            return true;
        } catch (Exception e) {
            // Handle exceptions here (e.g., log the error)
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean completeTwoFa(Integer mobile, Integer lender_id, String two_fa_value) {
        String actual2Fa=paUserLenderDao.getTwoFaValue(mobile, lender_id);
        return two_fa_value==actual2Fa;
    }


    @Override
    public boolean otpVerification(Integer otp, UUID transaction_id) {

        //update the code later to allow 3 retries.
            MapSqlParameterSource params= new MapSqlParameterSource();
            params.addValue("transaction_id", transaction_id);

        if(otp!=1234) {

            namedParameterJdbcTemplate.update(
                    "UPDATE transactions " +
                            "SET status=failed " +
                            "WHERE transaction_id=:transaction_id",
                    params
            );
            return false;
        }

        namedParameterJdbcTemplate.update(
                "UPDATE transactions " +
                        "SET status=completed " +
                        "WHERE transaction_id=:transaction_id",
                params
        );
        return true;
    }

    @Override
    public String sendSMS(UUID transaction_id) {
        MapSqlParameterSource params= new MapSqlParameterSource();
        params.addValue("transaction_id", transaction_id);

        Transaction transaction = namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM transactions WHERE transaction_id = :transaction_id",
                params, new BeanPropertyRowMapper<>(Transaction.class)
        );

        params.addValue("mobile", transaction.getMobile());

        User user=namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM users " +
                        "WHERE mobile= :mobile",
                params, new BeanPropertyRowMapper<>(User.class)
        );

        String sms="Hi "+user.getFirst_name()+" "+user.getLast_name()+
                ", Your payment of INR "+transaction.getTotal_amount() +
                "has been successful!\n";
        return sms;
    }

}
