package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.IPaUserLenderDao;
import com.flexmoney.projectfm.dao.ISessionDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class SessionDaoImpl implements ISessionDao {

    static String CANCEL_QUERY="UPDATE transactions SET status='failed' WHERE transaction_id= :transaction_id";
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private IPaUserLenderDao IPaUserLenderDao;

    @Override
    public ResponseEntity<String> initSession(String mobile, BigDecimal amount, HttpSession session) {
        // Store sensitive information in the session
        session.setAttribute("mobile", mobile);
        session.setAttribute("amount", amount);

        // Send session ID to the client in the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Session-ID", session.getId());

        return new ResponseEntity<>("Session initiated successfully!", headers, HttpStatus.OK);
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
                    CANCEL_QUERY,
                    params
            );

            session.invalidate();

            return true;
        } catch(Error e){
            return false;
        }
    }

}
