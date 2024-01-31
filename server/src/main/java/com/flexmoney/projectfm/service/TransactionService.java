package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.LenderDao;
import com.flexmoney.projectfm.dao.SessionDao;
import com.flexmoney.projectfm.dao.TransactionDao;
import com.flexmoney.projectfm.model.Lender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private LenderDao lenderDao;


    public String getLenders(String mobile, BigDecimal amount) {
        List<Lender> lenderList = lenderDao.fetchLenders(mobile, amount);
        return lenderDao.fetchTenures(lenderList);
    }

    public String initSession(String mobile, BigDecimal amount) {
        return sessionDao.initSession(mobile, amount);
    }

    public boolean setTransaction(UUID session_id, UUID lender_id, Integer tenure, BigDecimal interest_rate, String mobile, BigDecimal amount) {
        UUID transaction_id=UUID.randomUUID();
        sessionDao.addTransactionId(session_id, transaction_id);
        return transactionDao.setTransaction(transaction_id, lender_id, tenure, interest_rate, mobile, amount);
    }

    public boolean complete2Fa(String mobile, Integer lender_id, String two_fa_value) {
        return transactionDao.completeTwoFa(mobile, lender_id, two_fa_value);
    }

    public boolean otpVerification(Integer otp, UUID transaction_id) {
        return transactionDao.otpVerification(otp, transaction_id);
    }

    public String sendSMS(UUID transaction_id){
        return transactionDao.sendSMS(transaction_id);
    }

    public  boolean cancelSession(UUID session_id){
        return sessionDao.cancelSession(session_id);
    }
}
