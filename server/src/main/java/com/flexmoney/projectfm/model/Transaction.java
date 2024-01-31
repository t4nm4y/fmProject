package com.flexmoney.projectfm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private UUID transaction_id;
    private Integer mobile;
    private BigDecimal total_amount;
    private Date date_of_transaction;
    private Integer lender_id;
    private Integer tenure;
    private BigDecimal interest_rate;
    private String status;
    private Time init_time;
}
