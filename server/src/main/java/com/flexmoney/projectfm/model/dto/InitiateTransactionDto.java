package com.flexmoney.projectfm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTransactionDto {
    private Integer lender_id;
    private String two_fa_value;
    private Integer tenure;
    private BigDecimal interest_rate;
}
