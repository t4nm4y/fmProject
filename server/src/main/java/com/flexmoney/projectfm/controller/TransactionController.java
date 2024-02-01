package com.flexmoney.projectfm.controller;

import com.flexmoney.projectfm.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SplittableRandom;
import java.util.UUID;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initSession")
    public String initSession(@RequestParam String mobile, @RequestParam BigDecimal amount, HttpSession session) {
        return transactionService.initSession(session, mobile, amount);
    }

    @PostMapping("/getLenders")
    public String getLenders(HttpSession session) {
       return transactionService.getLenders(session);
    }

    @PostMapping("/completeTwoFa")
    public boolean completeTwoFa(@RequestBody Map<String, Object> request, HttpSession session) {
        Integer lender_id = (Integer) request.get("lender_id");
        String two_fa_value = (String) request.get("two_fa_value");
        return transactionService.complete2Fa(session, lender_id, two_fa_value);
    }

    @PostMapping("/setTransaction")
    public boolean setTransaction(@RequestBody Map<String, Object> request, HttpSession session) {

        Integer lender_id = (Integer) request.get("lender_id");
        Integer tenure = (Integer) request.get("tenure");
        BigDecimal interest_rate = new BigDecimal(request.get("interest_rate").toString());

        return transactionService.setTransaction(session, lender_id, tenure, interest_rate);
    }

    @PostMapping("/otp/{otp}")
    public boolean otpVerification(@RequestParam Integer otp, HttpSession session) {
        return transactionService.otpVerification(session, otp);
    }

    @PostMapping("/sms")
    public String sendSMS(HttpSession session) {
        return transactionService.sendSMS(session);
    }
    @PostMapping("/cancel")
    public boolean cancel(HttpSession session) {
        return transactionService.cancelSession(session);
    }
}


