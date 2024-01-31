package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.UserDao;
import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import com.flexmoney.projectfm.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao usersDao;

    @Autowired
    private PaUserLenderDao paUserLenderDao;

//    public User convertToUser(UserDto userDTO) {
//        User user = new User();
//        user.setMobile(userDTO.getMobile());
//        user.setFirst_name(userDTO.getFirst_name());
//        user.setLast_name(userDTO.getLast_name());
//        return user;
//    }
//
//    private PaUserLender convertToPaUser(UserDto userDTO) {
//        PaUserLender paUserLender = new PaUserLender();
//        paUserLender.setMobile(userDTO.getMobile());
//        paUserLender.setLender_id(userDTO.getLender_id());
//        paUserLender.setCredit_limit(userDTO.getCredit_limit());
//        paUserLender.setAvailable_credit_limit(userDTO.getCredit_limit());
//        paUserLender.setTwo_fa_value(userDTO.getTwo_fa_value());
//        return paUserLender;
//    }

    public String addUser(User user) {
        return usersDao.addUser(user) + " added successfully";
    }
    public String addPaUser(PaUserLender paUserLender){
        if(!usersDao.doesUserExist(paUserLender.getMobile())) return "User does not exist";
        return paUserLenderDao.addPaLender(paUserLender)+"added successfully";
    }
    public String deleteUser(String mobile){
        return usersDao.deleteUser(mobile);
    }

}
