package com.flexmoney.projectfm.controller;

import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import com.flexmoney.projectfm.model.dto.UserDto;
import com.flexmoney.projectfm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user){
        return userService.addUser(user);
    }

    @PostMapping("/addPaUser")
    public String addUser(@RequestBody PaUserLender paUserLender){
        return userService.addPaUser(paUserLender);
    }

    @DeleteMapping("/delete/{mobile}")
    public String delete(@PathVariable String mobile) {
        return userService.deleteUser(mobile);
    }
}
