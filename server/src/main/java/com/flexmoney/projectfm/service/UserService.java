package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.IPaUserLenderDao;
import com.flexmoney.projectfm.dao.IUserDao;
import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao usersDao;

    @Autowired
    private IPaUserLenderDao IPaUserLenderDao;

    public String addUser(User user) {
        String mobile=(String) user.getMobile();
        if(usersDao.doesUserExist(mobile)) return "User already exists";
        return usersDao.addUser(user);
    }
    public String addPaUser(PaUserLender paUserLender){
        String mobile=(String) paUserLender.getMobile();
        if(!usersDao.doesUserExist(mobile)) return "User doesn't exist!";
        return IPaUserLenderDao.addPaLender(paUserLender);
    }
    public String deleteUser(String mobile){
        if(!usersDao.doesUserExist(mobile)) return "User doesn't exist!";
        IPaUserLenderDao.deletePaUser(mobile);
        return usersDao.deleteUser(mobile);
    }

}
