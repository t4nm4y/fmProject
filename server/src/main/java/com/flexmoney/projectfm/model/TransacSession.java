package com.flexmoney.projectfm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacSession {
    private UUID sessionId;
    private UUID transactionId;
    private int mobile;
    private BigDecimal totalAmount;
}
