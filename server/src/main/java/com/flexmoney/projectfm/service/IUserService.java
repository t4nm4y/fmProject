package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;

public interface IUserService {
    String addUser(User user);
    String addPaUser(PaUserLender paUserLender);
    String deleteUser(String mobile);
}

