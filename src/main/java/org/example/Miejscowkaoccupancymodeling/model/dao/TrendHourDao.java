package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendHourDao extends JpaRepository<TrendHourEntity, Long> {
}
