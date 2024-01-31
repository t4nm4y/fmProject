package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.User;


public interface UserDao {
    public String addUser(User user);
    public String deleteUser(String mobile);
    public boolean doesUserExist(String mobile);
}
