package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.Lender;

import java.math.BigDecimal;
import java.util.List;


public interface ILenderDao {
    public List<Lender> fetchLenders(String mobile, BigDecimal amount);
    public String fetchTenures(List<Lender> lenderList, BigDecimal amount, String mobile);
}

