package com.urakovzhanat.service;

import com.urakovzhanat.entity.Income;
import com.urakovzhanat.entity.Spend;
import com.urakovzhanat.repository.IncomeRepository;
import com.urakovzhanat.repository.SpendRepository;
import com.urakovzhanat.repository.StatsRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {
    @InjectMocks
    private StatsService statsService;

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private SpendRepository spendRepository;

    @BeforeEach
    void setUp() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterEach
    void tearDown() {
        System.out.println(System.currentTimeMillis());
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @DisplayName("Incomes_Between_Dates_test")
    @Test
    void getStatsIncomesBetweenDates() throws ParseException {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> expectedList = new ArrayList<>() {
            {
                add("2023-10-04: 1300");
                add("2023-10-05: 1509");
            }
        };

        List<Income> returnList = new ArrayList<>();
        returnList.add(new Income(){{
            setIncome(BigDecimal.valueOf(1300));
            setDate(dateFormat.parse("2023-10-04"));
        }});
        returnList.add(new Income(){{
            setIncome(BigDecimal.valueOf(1509));
            setDate(dateFormat.parse("2023-10-05"));
        }});

        Mockito.when(incomeRepository.findByDateBetween(
                        Date.valueOf("2023-10-04"),
                        Date.valueOf("2023-10-05")
                ))
                .thenReturn(returnList);

        List<String> actualList = statsService.getStatsIncomesBetweenDates(from, to);

        Assertions.assertLinesMatch(expectedList, actualList);
    }
    @DisplayName("Spends_Between_Dates_test")
    @Test
    void getStatsSpendsBetweenDates() throws ParseException {

        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> expectedList = new ArrayList<>() {
            {
                add("2023-10-04: 450");
            }
        };

        List<Spend> returnList = new ArrayList<>();
        returnList.add(new Spend(){{
            setSpend(BigDecimal.valueOf(450));
            setDate(dateFormat.parse("2023-10-04"));
        }});

        Mockito.when(spendRepository.findByDateBetween(
                        Date.valueOf("2023-10-04"),
                        Date.valueOf("2023-10-05")
                ))
                .thenReturn(returnList);

        List<String> actualList = statsService.getStatsSpendsBetweenDates(from, to);
        Assertions.assertLinesMatch(expectedList, actualList);
    }
}