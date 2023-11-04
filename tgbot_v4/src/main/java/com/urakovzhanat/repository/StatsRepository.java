package com.urakovzhanat.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StatsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int getCountOfIncomesThatGreaterThan(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM INCOMES WHERE income > :amount",
                parameters,
                new StatsRowMapper());
    }

    public int getCountOfSpendsThatGreater(Long amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "select count(*) from SPEND where spend > :amount",
                parameters,
                new StatsRowMapper()
        );
    }

    public List<Map<String, Object>> getStatsSpendsBetweenDates(Date from, Date to) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", from);
        parameters.put("to", to);
        return namedParameterJdbcTemplate.queryForList(
                "SELECT SPEND, DATE FROM public.spend where date >= :from and date <= :to",
                parameters
        );
    }


    public List<Map<String, Object>> getStatsIncomesBetweenDates(Date from, Date to) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", from);
        parameters.put("to", to);
        return namedParameterJdbcTemplate.queryForList(
                "SELECT INCOME, DATE FROM public.incomes where date >= :from and date <= :to",
                parameters
        );
    }

    private static final class StatsRowMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("COUNT");
        }

        private static final class StatsDatesRowMapper implements RowMapper<String> {
            @Override
            public String mapRow(ResultSet resultSets, int rowNum) throws SQLException {
                return String.valueOf(resultSets.getInt("INCOME")) + " " + String.valueOf(resultSets.getString("DATE"));
            }
        }
    }
}
