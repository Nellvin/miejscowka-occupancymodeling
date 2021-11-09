package org.example.Miejscowkaoccupancymodeling.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OccupancyDao extends JpaRepository<OccupancyEntity, OccupancyId> {

    Optional<OccupancyEntity> findByIdPlaceId(Long placeId);

    boolean existsOccupancyEntityById(OccupancyId occupancyId);
}
