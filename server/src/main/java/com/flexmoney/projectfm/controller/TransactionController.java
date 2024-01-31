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
import java.util.UUID;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initSession")
    public String initSession(@RequestBody Integer mobile, BigDecimal amount) {
        return transactionService.initSession(mobile, amount);
    }

    @PostMapping("/updateSession")
    public String updateSession(@RequestBody UUID session_id, UUID transac_id) {
        return transactionService.updateSession(session_id, transac_id);
    }

    @PostMapping("/getLenders")
    public String getLenders(@RequestBody Map<String, Object> request) {
        Integer mobile = (Integer) request.get("mobile");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        return transactionService.getLenders(mobile, amount);
    }

    @PostMapping("/completeTwoFa")
    public boolean completeTwoFa(@RequestBody Integer mobile, Integer lender_id, String two_fa_value) {
        return transactionService.complete2Fa(mobile, lender_id, two_fa_value);
    }

    @PostMapping("/setTransaction")
    public boolean setTransaction(@RequestBody Map<String, Object> request) {
        UUID session_id = (UUID) request.get("session_id");
        UUID lender_id = (UUID) request.get("lender_id");
        Integer tenure = (Integer) request.get("tenure");
        BigDecimal interest_rate = new BigDecimal(request.get("interest_rate").toString());

        return transactionService.setTransaction(session_id, lender_id, tenure, interest_rate);
    }

    @PostMapping("/otp")
    public boolean otpVerification(@RequestBody Map<String, Object> request) {
        UUID session_id = (UUID) request.get("session_id");
        Integer otp = (Integer) request.get("otp");
        return transactionService.otpVerification(otp, session_id);
    }

    @PostMapping("/sms")
    public String sendSMS(@RequestBody Map<String, Object> request) {
        UUID session_id = (UUID) request.get("session_id");
        return transactionService.sendSMS(session_id);
    }
}


