package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PlayerStatistics;

@Repository
public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics, Long> {
}

