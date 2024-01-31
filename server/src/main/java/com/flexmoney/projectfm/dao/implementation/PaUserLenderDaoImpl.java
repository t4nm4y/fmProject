package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.model.PaUserLender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaUserLenderDaoImpl implements PaUserLenderDao {
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String addPaLender(PaUserLender paUserLender) {

        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("mobile", paUserLender.getMobile());
        params.addValue("lender_id", paUserLender.getLender_id());
        params.addValue("credit_limit", paUserLender.getCredit_limit());
        params.addValue("2fa_value", paUserLender.getTwo_fa_value());

        return namedParameterJdbcTemplate.update(
                "INSERT INTO pa_users " +
                        "(mobile, lender_id, available_credit_limit, credit_limit, two_fa_value) " +
                    "VALUES(:mobile,:lender_id,:credit_limit,:credit_limit,:2fa_value)",
                    params
                ) +
                " added PA user";
    }

    @Override
    public String getTwoFaValue(Integer mobile, Integer lender_id) {
        try {
            MapSqlParameterSource params=new MapSqlParameterSource();
            params.addValue("mobile", mobile);
            params.addValue("lender_id", lender_id);

            return namedParameterJdbcTemplate.queryForObject(
                    "SELECT two_fa_value FROM pa_users WHERE mobile=:mobile AND lender_id=:lender_id",
                    params, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
