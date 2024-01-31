package com.flexmoney.projectfm.dao;

import java.math.BigDecimal;
import java.util.UUID;


public interface SessionDao {
    public String initSession(Integer mobile, BigDecimal amount);
    public boolean addTransactionId(UUID session_id, UUID transaction_id);
    public boolean cancelSession(UUID session_id);
}
