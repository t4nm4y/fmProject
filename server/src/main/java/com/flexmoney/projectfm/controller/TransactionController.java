package com.flexmoney.projectfm.controller;

import com.flexmoney.projectfm.model.dto.CreateSessionDto;
import com.flexmoney.projectfm.model.dto.InitiateTransactionDto;
import com.flexmoney.projectfm.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/transaction/create-session")
    public ResponseEntity<String> initSession(@RequestBody CreateSessionDto createSessionDto, HttpSession session) {
        String mobile=createSessionDto.getMobile();
        BigDecimal amount=createSessionDto.getAmount();

        return transactionService.initSession(session, mobile, amount);
    }

    @GetMapping("/transaction/get-lenders")
    public String getLenders(HttpSession session) {
        return transactionService.getLenders(session);
    }

    @PostMapping("/transaction/initiate")
    public boolean completeTwoFa(@RequestBody InitiateTransactionDto initiateTransactionDto, HttpSession session) {
        Integer lender_id = initiateTransactionDto.getLender_id();
        String two_fa_value = initiateTransactionDto.getTwo_fa_value();
        Integer tenure = initiateTransactionDto.getTenure();
        BigDecimal interest_rate = initiateTransactionDto.getInterest_rate();

        if(!transactionService.completeTwoFa(session, lender_id, two_fa_value)) return false;
        return transactionService.setTransaction(session, lender_id, tenure, interest_rate);
    }

    @PostMapping("/transaction/verify-otp/{otp}")
    public boolean otpVerification(@RequestParam Integer otp, HttpSession session) {
        return transactionService.otpVerification(session, otp);
    }

    @GetMapping("/transaction/send-sms")
    public String sendSMS(HttpSession session) {
        return transactionService.sendSMS(session);
    }
    @GetMapping("/transaction/cancel")
    public boolean cancel(HttpSession session) {
        return transactionService.cancelSession(session);
    }
}


