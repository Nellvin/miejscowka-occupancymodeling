package org.example.Miejscowkaoccupancymodeling.logic.mapper;

import org.example.Miejscowkaoccupancymodeling.logic.to.TrendTo;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrendMapper {
    TrendTo toTrendTo(TrendEntity trend);
}
