package com.flexmoney.projectfm.dao.implementation;

import com.flexmoney.projectfm.dao.UserDao;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addUser(User user) {
        return jdbcTemplate.update("INSERT INTO users (mobile, first_name, last_name) VALUES (?, ?, ?)",
                user.getMobile(), user.getFirst_name(), user.getLast_name())
        +" added Successfully ";
    }

    @Override
    public String deleteUser(Integer mobile){
        return jdbcTemplate.update("DELETE FROM users " +
                "WHERE mobile = ?", mobile)+"Deleted Successfully";
    }
    @Override
    public boolean doesUserExist(Integer mobile){
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE mobile = ?",
                Integer.class,
                mobile);

        return count != null && count > 0;
    }
}
