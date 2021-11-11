package org.example.Miejscowkaoccupancymodeling.logic.mapper;

import org.example.Miejscowkaoccupancymodeling.logic.to.OccupancyTo;
import org.example.Miejscowkaoccupancymodeling.model.entity.OccupancyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccupancyMapper {
    OccupancyTo toOccupancyTo(OccupancyEntity occupancyEntity);
}
