package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendDao extends JpaRepository<TrendEntity, Long> {
}
