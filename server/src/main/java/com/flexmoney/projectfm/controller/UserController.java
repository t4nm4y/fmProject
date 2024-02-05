package com.flexmoney.projectfm.controller;

import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import com.flexmoney.projectfm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

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
