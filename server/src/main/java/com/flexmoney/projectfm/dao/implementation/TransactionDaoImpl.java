package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.IPaUserLenderDao;
import com.flexmoney.projectfm.dao.ITransactionDao;
import com.flexmoney.projectfm.model.Transaction;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private IPaUserLenderDao IPaUserLenderDao;

    @Override
    public boolean setTransaction(UUID transaction_id, Integer lender_id, Integer tenure, BigDecimal interest_rate, String mobile, BigDecimal amount) {
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
            return false;
        }
    }

    @Override
    public boolean completeTwoFa(String mobile, Integer lender_id, String two_fa_value) {
        String actual2Fa= IPaUserLenderDao.getTwoFaValue(mobile, lender_id);
        return two_fa_value==actual2Fa;
    }


    @Override
    public boolean otpVerification(Integer otp, UUID transaction_id, String mobile, BigDecimal amount) {

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
        Integer lender_id = namedParameterJdbcTemplate.queryForObject(
                "SELECT lender_id FROM transactions WHERE transaction_id = :transaction_id",
                params, new BeanPropertyRowMapper<>(Integer.class)
        );
        params.addValue("lender_id", lender_id);
        params.addValue("amount", amount);
        namedParameterJdbcTemplate.update(
                "UPDATE pa_users " +
                        "SET available_credit_limit=available_credit_limit-:amount " +
                        "WHERE lender_id=:lender_id AND mobile=:mobile",
                params
        );
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
                ", Your payment of INR "+transaction.getTotalAmount() +
                "has been successful!\n";
        return sms;
    }

}
