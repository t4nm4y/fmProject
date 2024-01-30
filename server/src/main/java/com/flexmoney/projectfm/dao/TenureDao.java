package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.Tenure;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public interface TenureDao {
    public List<Tenure> fetchTenures(UUID lender_id, BigDecimal amount);
}
