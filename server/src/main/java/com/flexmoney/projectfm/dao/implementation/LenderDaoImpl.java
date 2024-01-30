package com.flexmoney.projectfm.dao.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexmoney.projectfm.dao.LenderDao;
import com.flexmoney.projectfm.model.Lender;
import com.flexmoney.projectfm.model.Tenure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LenderDaoImpl implements LenderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Lender> fetchLenders(Integer mobile, BigDecimal amount) {
        return jdbcTemplate.query(
                "SELECT * FROM lenders l " +
                        "JOIN pa_users p ON p.lender_id = l.lender_id " +
                        "WHERE p.mobile = ? AND ? BETWEEN l.min_trans_limit AND l.max_trans_limit",
                new BeanPropertyRowMapper<>(Lender.class), mobile, amount);
    }

    public String fetchTenures(List<Lender> lenderList) {
        String sql = "SELECT * FROM tenures t JOIN lenders l ON t.lender_id = l.lender_id " +
                "WHERE l.lender_id IN (:lenderIds)";

        // Extract lender IDs from the list
        List<Integer> lenderIds = lenderList.stream().map(Lender::getLender_id).collect(Collectors.toList());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("lenderIds", lenderIds);

        List<Tenure> tenures = namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(Tenure.class));

        // Organize the data as a Map with lender_id as key and list of tenures as value
        Map<Integer, List<Tenure>> tenuresMap = tenures.stream().collect(Collectors.groupingBy(Tenure::getLender_id));

        // Convert the Map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(tenuresMap);
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            return "{\"error\": \"Error processing JSON\"}";
        }
    }
}

