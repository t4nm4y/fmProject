package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.ILenderDao;
import com.flexmoney.projectfm.dao.ISessionDao;
import com.flexmoney.projectfm.dao.ITransactionDao;
import com.flexmoney.projectfm.model.Lender;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ISessionDao ISessionDao;

    @Autowired
    private ITransactionDao ITransactionDao;

    @Autowired
    private ILenderDao ILenderDao;


    public String getLenders(HttpSession session) {
        String mobile = (String) session.getAttribute("mobile");
        BigDecimal amount = (BigDecimal) session.getAttribute("amount");

        System.out.println("\n\nzzzzzzzzzzzzzzzzzzzzzzzzzzz\n\n fetched values: " + mobile + "  "+amount);

        List<Lender> lenderList = ILenderDao.fetchLenders(mobile, amount);
        System.out.println("\n Lenders list: " + lenderList);

        return ILenderDao.fetchTenures(lenderList, amount, mobile);
    }

//    public String getLendersMock(HttpSession session){
//        String mobile="1234567892";
//        BigDecimal amount=new BigDecimal("20000");
//        List<Lender> lenderList = ILenderDao.fetchLenders(mobile, amount);
//        System.out.println("\n Lenders list: " + lenderList);
//
//        return ILenderDao.fetchTenures(lenderList, amount, mobile);
//    }

    public ResponseEntity<String> initSession(HttpSession session, String mobile, BigDecimal amount) {
        return ISessionDao.initSession(mobile, amount, session);
    }

    public boolean completeTwoFa(HttpSession session, Integer lender_id, String two_fa_value) {
        String mobile=(String) session.getAttribute("mobile");
        return ITransactionDao.completeTwoFa(mobile, lender_id, two_fa_value);
    }

    public boolean setTransaction(HttpSession session, Integer lender_id, Integer tenure, BigDecimal interest_rate) {
        UUID transaction_id=UUID.randomUUID();
        session.setAttribute("transaction_id", transaction_id);
        String mobile = (String) session.getAttribute("mobile");
        BigDecimal amount= (BigDecimal) session.getAttribute("amount");

        return ITransactionDao.setTransaction(transaction_id, lender_id, tenure, interest_rate, mobile, amount);
    }


    public boolean otpVerification(HttpSession session, Integer otp) {
        UUID transaction_id=(UUID) session.getAttribute("transaction_id");
        String mobile = (String) session.getAttribute("mobile");
        BigDecimal amount= (BigDecimal) session.getAttribute("amount");

        return ITransactionDao.otpVerification(otp, transaction_id, mobile, amount);
    }

    public String sendSMS(HttpSession session){
        UUID transaction_id=(UUID) session.getAttribute("transaction_id");
        return ITransactionDao.sendSMS(transaction_id);
    }

    public  boolean cancelSession(HttpSession session){
        return ISessionDao.cancelSession(session);
    }
}
