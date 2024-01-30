package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.PaUserLender;


public interface PaUserLenderDao {

    //to add a pa lender of an existing user
    public String addPaLender(PaUserLender paUserLender);
    public String getTwoFaValue(Integer mobile, Integer lender_id);
}
