package com.flexmoney.projectfm.dao;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.UUID;


public interface SessionDao {
    public String initSession(String mobile, BigDecimal amount, HttpSession session);
    public boolean cancelSession(HttpSession session);
}
