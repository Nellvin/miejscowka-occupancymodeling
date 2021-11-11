package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendDayDao extends JpaRepository<TrendDayEntity, Long> {
}
