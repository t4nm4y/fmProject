package com.flexmoney.projectfm.controller;

import com.flexmoney.projectfm.dao.TransacSessionDao;
import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import com.flexmoney.projectfm.service.TransactionService;
import com.flexmoney.projectfm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initSession")
    public String initSession(@RequestBody Integer mobile, BigDecimal amount){
       return transactionService.initSession(mobile, amount);
    }

    @PostMapping("/getLenders")
    public String getLenders(@RequestBody Map<String, Object> request){
        Integer mobile = (Integer) request.get("mobile");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        return transactionService.getLenders(mobile, amount);
    }
}
