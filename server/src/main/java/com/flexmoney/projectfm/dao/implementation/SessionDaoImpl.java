package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class SessionDaoImpl implements SessionDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PaUserLenderDao paUserLenderDao;

    @Override
    public String initSession(Integer mobile, BigDecimal amount) {
        UUID session_id = UUID.randomUUID();

        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("session_id", session_id);
        params.addValue("mobile", mobile);
        params.addValue("amount", amount);

        return namedParameterJdbcTemplate.update(
                "INSERT INTO sessions " +
                        "(session_id, mobile, amount) " +
                        "VALUES (:session_id, :mobile, :amount)",
                params)
        +" created Successfully ";
    }

    @Override
    public boolean addTransactionId(UUID session_id, UUID transaction_id) {

        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("session_id", session_id);
        params.addValue("transaction_id", transaction_id);

        int numRowsUpdated = namedParameterJdbcTemplate.update(
                "UPDATE sessions " +
                        "SET transaction_id = :transaction_id" +
                        "WHERE session_id = :session_id",
                params);

        if (numRowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancelSession(UUID session_id) {
        try{
            MapSqlParameterSource params=new MapSqlParameterSource();
            params.addValue("session_id", session_id);

            UUID transaction_id =namedParameterJdbcTemplate.queryForObject(
                    "SELECT transaction_id FROM sessions " +
                            "WHERE session_id= :session_id",
                    params, UUID.class
            );

            params.addValue("transaction_id", transaction_id);

            namedParameterJdbcTemplate.update(
                    "UPDATE transactions " +
                            "SET status=failed " +
                            "WHERE transaction_id= :transaction_id",
                    params
            );
            return true;
        } catch(Error e){
            return false;
        }
    }

}
