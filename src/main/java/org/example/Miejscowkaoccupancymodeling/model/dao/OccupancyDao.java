package org.example.Miejscowkaoccupancymodeling.model.dao;

import org.example.Miejscowkaoccupancymodeling.model.entity.OccupancyEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.OccupancyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OccupancyDao extends JpaRepository<OccupancyEntity, OccupancyId> {

    Optional<Set<OccupancyEntity>> findByIdPlaceId(Long placeId);

    boolean existsOccupancyEntityById(OccupancyId occupancyId);

    Optional<Set<OccupancyEntity>> findByActiveAndIdPlaceId(boolean active, Long placeId);
}
