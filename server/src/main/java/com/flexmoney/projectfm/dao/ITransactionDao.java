package com.flexmoney.projectfm.dao;

import java.math.BigDecimal;
import java.util.UUID;


public interface ITransactionDao {
    public boolean setTransaction(UUID transaction_id, Integer lender_id, Integer tenure, BigDecimal interest_rate, String mobile, BigDecimal amount);
    public boolean completeTwoFa(String mobile, Integer lender_id, String two_fa_value);
    public boolean otpVerification(Integer otp, UUID transaction_id, String mobile, BigDecimal amount);
    public String sendSMS(UUID transaction_id);
}
