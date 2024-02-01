package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.SessionDao;
import jakarta.servlet.http.HttpSession;
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
    public String initSession(String mobile, BigDecimal amount, HttpSession session) {

        String session_id = (String) session.getAttribute("session_id");
        if (session_id == null) {
            session_id = UUID.randomUUID().toString();
            session.setAttribute("session_id", session_id);
        }
        session.setAttribute("mobile", mobile);
        session.setAttribute("amount", amount);
        return "Session initiated successfully!";
    }


    @Override
    public boolean cancelSession(HttpSession session) {
        try{
            MapSqlParameterSource params=new MapSqlParameterSource();
//            params.addValue("session_id", session_id);
//
//            UUID transaction_id =namedParameterJdbcTemplate.queryForObject(
//                    "SELECT transaction_id FROM sessions " +
//                            "WHERE session_id= :session_id",
//                    params, UUID.class
//            );

            UUID transaction_id=(UUID) session.getAttribute("transaction_id");

            params.addValue("transaction_id", transaction_id);

            namedParameterJdbcTemplate.update(
                    "UPDATE transactions " +
                            "SET status=failed " +
                            "WHERE transaction_id= :transaction_id",
                    params
            );

            session.invalidate();

            return true;
        } catch(Error e){
            return false;
        }
    }

}
