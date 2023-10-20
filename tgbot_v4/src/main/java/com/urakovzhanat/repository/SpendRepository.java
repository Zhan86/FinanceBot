package com.urakovzhanat.repository;

import com.urakovzhanat.entity.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
}
