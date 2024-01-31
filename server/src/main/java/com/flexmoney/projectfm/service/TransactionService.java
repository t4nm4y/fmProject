package com.flexmoney.projectfm.service;

import com.flexmoney.projectfm.dao.LenderDao;
import com.flexmoney.projectfm.dao.PaUserLenderDao;
import com.flexmoney.projectfm.dao.TransacSessionDao;
import com.flexmoney.projectfm.dao.UserDao;
import com.flexmoney.projectfm.model.Lender;
import com.flexmoney.projectfm.model.PaUserLender;
import com.flexmoney.projectfm.model.User;
import com.flexmoney.projectfm.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransacSessionDao transacSessionDao;

    @Autowired
    private LenderDao lenderDao;


    public String getLenders(Integer mobile, BigDecimal amount) {
        List<Lender> lenderList = lenderDao.fetchLenders(mobile, amount);
        return lenderDao.fetchTenures(lenderList);
    }

    public String initSession(Integer mobile, BigDecimal amount) {
        return transacSessionDao.initSession(mobile, amount);
    }

    public String updateSession(UUID session_id, UUID transaction_id) {
        return transacSessionDao.updateSession(session_id, transaction_id);
    }

    public boolean complete2Fa(Integer mobile, Integer lender_id, String two_fa_value) {
        return transacSessionDao.completeTwoFa(mobile, lender_id, two_fa_value);
    }

    public boolean setTransaction(UUID session_id, UUID lender_id, Integer tenure, BigDecimal interest_rate) {
        return transacSessionDao.setTransaction(session_id, lender_id, tenure, interest_rate);
    }

    public boolean otpVerification(Integer otp, UUID session_id) {
        return transacSessionDao.otpVerification(otp, session_id);
    }

    public String sendSMS(UUID session_id){
        return transacSessionDao.sendSMS(session_id);
    }
}
