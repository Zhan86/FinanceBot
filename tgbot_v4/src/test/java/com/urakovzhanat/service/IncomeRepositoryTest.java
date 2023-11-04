package com.urakovzhanat.service;

import com.urakovzhanat.entity.Income;
import com.urakovzhanat.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IncomeRepositoryTest {
    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testRepo() {
        for (int i = 0; i < 10; i++, incomeRepository.save(new Income())) ;
        final List<Income> found = incomeRepository.findAll();
        assertEquals(10, found.size());
    }
}
