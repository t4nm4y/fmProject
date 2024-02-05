package com.flexmoney.projectfm.dao.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexmoney.projectfm.dao.ILenderDao;
import com.flexmoney.projectfm.model.Lender;
import com.flexmoney.projectfm.model.dto.TenureDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LenderDaoImpl implements ILenderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Lender> fetchLenders(String mobile, BigDecimal amount) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mobile", mobile);
        params.addValue("amount", amount);


        return namedParameterJdbcTemplate.query(
                "SELECT * FROM lenders l " +
                        "JOIN pa_users p ON p.lender_id = l.lender_id " +
//                        "WHERE p.mobile = :mobile AND p.available_credit_limit >= :amount AND :amount BETWEEN l.min_transaction_limit AND l.max_transaction_limit",
                "WHERE p.mobile = :mobile AND :amount BETWEEN l.min_transaction_limit AND l.max_transaction_limit",

                params, new BeanPropertyRowMapper<>(Lender.class));
    }

    public String fetchTenures(List<Lender> lenderList, BigDecimal amount, String mobile) {
        if (lenderList == null || amount == null) {
            return "{\"error\": \"Invalid input parameters\"}";
        }

        String sql = "SELECT t.*, l.lender_name, l.icon_link " +
                "FROM tenures t " +
                "JOIN lenders l ON t.lender_id = l.lender_id " +
                "WHERE l.lender_id IN (:lenderIds)";

        List<Integer> lenderIds = lenderList.stream().map(Lender::getLender_id).collect(Collectors.toList());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("lenderIds", lenderIds);

        List<TenureDetailsDto> TenureDetails = namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(TenureDetailsDto.class));

        TenureDetails.forEach(tenure -> {
            BigDecimal interestRate = tenure.getInterest_rate();
            Integer months = tenure.getTenure();
            BigDecimal emi = calculateEMI(amount, interestRate, months);
            BigDecimal totalAmount = emi.multiply(BigDecimal.valueOf(months));
            tenure.setEmi(emi);
            tenure.setTotal_amount(totalAmount);
        });

        Map<Integer, List<TenureDetailsDto>> tenuresMap = TenureDetails.stream().collect(Collectors.groupingBy(TenureDetailsDto::getLender_id));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("mobile", mobile);
        resultMap.put("amount", amount);
        resultMap.put("tenures", tenuresMap); // Include the existing tenures map

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Error processing JSON\"}";
        }
    }

    private BigDecimal calculateEMI(BigDecimal principal, BigDecimal interestRate, Integer tenure) {
        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(1200), MathContext.DECIMAL64); // Accurate division for interest rate
        BigDecimal emi = principal.multiply(monthlyInterestRate)
                .multiply(BigDecimal.ONE.add(monthlyInterestRate).pow(tenure))
                .divide(BigDecimal.ONE.add(monthlyInterestRate).pow(tenure).subtract(BigDecimal.ONE), MathContext.DECIMAL64);
        return emi.setScale(0, RoundingMode.CEILING);
    }
}

