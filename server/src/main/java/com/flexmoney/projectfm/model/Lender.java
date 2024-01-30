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
public class Lender {
    private Integer lender_id;
    private BigDecimal min_trans_limit;
    private BigDecimal max_trans_limit;
    private String two_fa_type;
    private String lender_name;
    private String icon_link;
}
