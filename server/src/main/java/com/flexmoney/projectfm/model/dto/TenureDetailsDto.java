package com.flexmoney.projectfm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenureDetailsDto {

        private Integer lender_id;
        private String lender_name;
        private String icon_link;
        private Integer tenure;
        private BigDecimal interest_rate;
        private BigDecimal emi;
        private BigDecimal total_amount;
}
