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
public class UserDto {
    private String mobile;
    private String first_name;
    private String last_name;
    private Integer lender_id;
    private BigDecimal credit_limit;
    private String two_fa_value;
}
