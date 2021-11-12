package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendDayEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrendDayDao extends JpaRepository<TrendDayEntity, Long> {

    Optional<TrendDayEntity> findTrendDayEntityByDay(int day);

    boolean existsByDayAndTrend(int day, TrendEntity trend);

    Optional<TrendDayEntity> findByDayAndTrend(int day, TrendEntity trend);
}
