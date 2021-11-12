package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.TrendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrendDao extends JpaRepository<TrendEntity, Long> {

    Optional<TrendEntity> findByPlaceId(Long placeId);

    boolean existsByPlaceId(Long placeId);

    TrendEntity getByPlaceId(Long placeId);
}
