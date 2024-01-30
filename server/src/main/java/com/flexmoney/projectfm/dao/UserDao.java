package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.User;


public interface UserDao {
    public String addUser(User user);
    public String deleteUser(Integer mobile);
    public boolean doesUserExist(Integer mobile);
}
