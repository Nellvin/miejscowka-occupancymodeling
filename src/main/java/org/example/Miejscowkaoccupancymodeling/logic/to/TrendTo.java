package org.example.Miejscowkaoccupancymodeling.logic.to;

import java.util.Set;

public class TrendTo {

    private Long placeId;

    private Set<TrendDayTo> trendDayEntities;

    public TrendTo(Long placeId, Set<TrendDayTo> trendDayEntities) {
        this.placeId = placeId;
        this.trendDayEntities = trendDayEntities;
    }

    public TrendTo() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Set<TrendDayTo> getTrendDayEntities() {
        return trendDayEntities;
    }

    public void setTrendDayEntities(Set<TrendDayTo> trendDayEntities) {
        this.trendDayEntities = trendDayEntities;
    }
}
