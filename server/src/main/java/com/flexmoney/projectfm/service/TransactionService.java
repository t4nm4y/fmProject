package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.LenderDao;
import com.flexmoney.projectfm.dao.SessionDao;
import com.flexmoney.projectfm.dao.TransactionDao;
import com.flexmoney.projectfm.model.Lender;
import jakarta.servlet.http.HttpSession;
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


    public String getLenders(HttpSession session) {
        String mobile = (String) session.getAttribute("mobile");
        BigDecimal amount = (BigDecimal) session.getAttribute("amount");

        List<Lender> lenderList = lenderDao.fetchLenders(mobile, amount);

        return lenderDao.fetchTenures(lenderList);
    }

    public String initSession(HttpSession session, String mobile, BigDecimal amount) {
        return sessionDao.initSession(mobile, amount, session);
    }

    public boolean setTransaction(HttpSession session, Integer lender_id, Integer tenure, BigDecimal interest_rate) {
        UUID transaction_id=UUID.randomUUID();
        session.setAttribute("transaction_id", transaction_id);
        String mobile = (String) session.getAttribute("mobile");
        BigDecimal amount= (BigDecimal) session.getAttribute("amount");

        return transactionDao.setTransaction(transaction_id, lender_id, tenure, interest_rate, mobile, amount);
    }

    public boolean complete2Fa(HttpSession session, Integer lender_id, String two_fa_value) {
        String mobile=(String) session.getAttribute("mobile");
        return transactionDao.completeTwoFa(mobile, lender_id, two_fa_value);
    }

    public boolean otpVerification(HttpSession session, Integer otp) {
        UUID transaction_id=(UUID) session.getAttribute("transaction_id");
        return transactionDao.otpVerification(otp, transaction_id);
    }

    public String sendSMS(HttpSession session){
        UUID transaction_id=(UUID) session.getAttribute("transaction_id");
        return transactionDao.sendSMS(transaction_id);
    }

    public  boolean cancelSession(HttpSession session){
        return sessionDao.cancelSession(session);
    }
}
