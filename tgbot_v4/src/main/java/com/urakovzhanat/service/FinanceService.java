package com.urakovzhanat.service;

import com.urakovzhanat.entity.Income;
import com.urakovzhanat.entity.Spend;
import com.urakovzhanat.repository.IncomeRepository;
import com.urakovzhanat.repository.SpendRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private static final String ADD_INCOME = "/addincome";

    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String message;
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            Income income = new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price));
            income.setDate(new Date(System.currentTimeMillis()));
            incomeRepository.save(income);
            message = "Доход в размере " + price + " был успешно добавлен";
        } else {
            Spend spend = new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price));
            spend.setDate(new Date(System.currentTimeMillis()));
            spendRepository.save(spend);
            message = "Расход в размере " + price + " был успешно добавлен";
        }
        return message;
    }

}
