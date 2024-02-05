package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ITransactionService {
    String getLenders(HttpSession session);
    ResponseEntity<String> initSession(HttpSession session, String mobile, BigDecimal amount);
    boolean completeTwoFa(HttpSession session, Integer lender_id, String two_fa_value);
    boolean setTransaction(HttpSession session, Integer lender_id, Integer tenure, BigDecimal interest_rate);
    boolean otpVerification(HttpSession session, Integer otp);
    String sendSMS(HttpSession session);
    boolean cancelSession(HttpSession session);
}

