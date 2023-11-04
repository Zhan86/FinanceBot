package com.urakovzhanat.repository;

import com.urakovzhanat.entity.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
    List<Spend> findByDateBetween(Date from, Date to);
}
