package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.Lender;
import com.flexmoney.projectfm.model.Tenure;

import java.math.BigDecimal;
import java.util.List;


public interface LenderDao {
    public List<Lender> fetchLenders(String mobile, BigDecimal amount);
    public String fetchTenures(List<Lender> lenderList);
}
