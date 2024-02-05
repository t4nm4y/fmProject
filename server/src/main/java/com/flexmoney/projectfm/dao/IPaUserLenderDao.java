package com.flexmoney.projectfm.dao;

import com.flexmoney.projectfm.model.PaUserLender;


public interface IPaUserLenderDao {

    //to add a pa lender of an existing user
    String addPaLender(PaUserLender paUserLender);
    String getTwoFaValue(String mobile, Integer lender_id);
    String deletePaUser(String mobile);
}
