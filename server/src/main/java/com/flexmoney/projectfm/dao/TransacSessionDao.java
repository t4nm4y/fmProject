package com.flexmoney.projectfm.dao;

import java.math.BigDecimal;
import java.util.UUID;


public interface TransacSessionDao {
    public String initSession(Integer mobile, BigDecimal amount);
    public String updateSession(UUID session_id, UUID transaction_id);

    public boolean completeTwoFa(Integer mobile, Integer lender_id, String two_fa_value);

}
