package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendDayEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrendHourDao extends JpaRepository<TrendHourEntity, Long> {

    Optional<TrendHourEntity> findByTrendDayAndHour(TrendDayEntity trendDay, int hour);

    boolean existsByHourAndTrendDay(int hour, TrendDayEntity trend);

    Optional<TrendHourEntity> findByHourAndTrendDay(int hour, TrendDayEntity trend);
}
