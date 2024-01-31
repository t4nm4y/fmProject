package com.flexmoney.projectfm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaUserLender {
    private String mobile;
    private Integer lender_id;
    private BigDecimal available_credit_limit;
    private BigDecimal credit_limit;
    private String two_fa_value;
    private String status;
}
