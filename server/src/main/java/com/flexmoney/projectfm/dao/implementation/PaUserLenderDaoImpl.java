package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.model.PaUserLender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaUserLenderDaoImpl implements PaUserLenderDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public String addPaLender(PaUserLender paUserLender) {
        return jdbcTemplate.update("INSERT INTO pa_users (mobile, lender_id, available_credit_limit, credit_limit, two_fa_value)"+
                    "VALUES(?,?,?,?,?)",
                    paUserLender.getMobile(), paUserLender.getLender_id(), paUserLender.getCredit_limit(), paUserLender.getCredit_limit(), paUserLender.getTwo_fa_value()
                )+" added PA user";
    }

    @Override
    public String getTwoFaValue(Integer mobile, Integer lender_id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT two_fa_value FROM pa_user_lender WHERE mobile=? AND lender_id=?",
                    String.class,
                    mobile, lender_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
