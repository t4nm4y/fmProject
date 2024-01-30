package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.TransacSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
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
}
