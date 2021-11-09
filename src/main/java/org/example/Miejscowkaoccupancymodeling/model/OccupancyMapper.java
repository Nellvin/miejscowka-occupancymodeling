package org.example.Miejscowkaoccupancymodeling.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccupancyMapper {
    OccupancyTo toOccupancyTo(OccupancyEntity occupancyEntity);
}
