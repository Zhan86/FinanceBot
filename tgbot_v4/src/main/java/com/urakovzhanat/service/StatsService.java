package com.urakovzhanat.service;

import com.urakovzhanat.entity.Income;
import com.urakovzhanat.entity.Spend;
import com.urakovzhanat.repository.IncomeRepository;
import com.urakovzhanat.repository.SpendRepository;
import com.urakovzhanat.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final StatsRepository statsRepository;
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public int getCountOfIncomesThanGreater(BigDecimal amount) {
        return statsRepository.getCountOfIncomesThatGreaterThan(amount);
    }

    public int getCountOfSpendsThatGreater(Long amount) {
        return statsRepository.getCountOfSpendsThatGreater(amount);
    }

    public List<String> getStatsIncomesBetweenDates(Date from, Date to) {
        List<String> result = new ArrayList<>();
        List<Income> incomes = incomeRepository.findByDateBetween(from, to);
        for (Income income : incomes) {
            result.add(new SimpleDateFormat("yyyy-MM-dd").format(income.getDate()) + ": " + income.getIncome());
        }
        return result;
    }

    public List<String> getStatsSpendsBetweenDates(Date from, Date to) {
        List<String> result = new ArrayList<>();
        List<Spend> spends = spendRepository.findByDateBetween(from, to);
        for (Spend spend : spends) {
            result.add(new SimpleDateFormat("yyyy-MM-dd").format(spend.getDate()) + ": " +  spend.getSpend());
        }
        return result;
    }
}
