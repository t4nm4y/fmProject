package com.flexmoney.projectfm.dao;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;


public interface ISessionDao {
    ResponseEntity<String> initSession(String mobile, BigDecimal amount, HttpSession session);
    boolean cancelSession(HttpSession session);
}
