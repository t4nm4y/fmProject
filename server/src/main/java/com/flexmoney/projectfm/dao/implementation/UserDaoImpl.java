package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.UserDao;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String addUser(User user) {

        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("mobile", user.getMobile());
        params.addValue("first_name", user.getFirst_name());
        params.addValue("last_name", user.getLast_name());

        System.out.println("\n\n\n\n\n\n"+params);
        return namedParameterJdbcTemplate.update(
                "INSERT INTO users (mobile, first_name, last_name) VALUES (:mobile, :first_name, :last_name)",
                params) + " added Successfully ";
    }

    @Override
    public String deleteUser(String mobile){
        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("mobile", mobile);

        int res=namedParameterJdbcTemplate.update(
                "DELETE FROM users " +
                "WHERE mobile = :mobile", params);
        if(res==0) return "User doesn't exist!";
        return "Deleted Successfully!";
    }
    @Override
    public boolean doesUserExist(String mobile){
        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("mobile", mobile);

        Integer count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE mobile = :mobile",
                params,
                Integer.class
        );

        return count != null && count > 0;
    }
}
