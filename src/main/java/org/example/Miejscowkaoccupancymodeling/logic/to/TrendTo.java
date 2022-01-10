package org.example.Miejscowkaoccupancymodeling.logic.to;

import java.util.List;

public class TrendTo {

    private Long placeId;

    private List<TrendDayTo> trendDayEntities;

    public TrendTo(Long placeId, List<TrendDayTo> trendDayEntities) {
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

    public List<TrendDayTo> getTrendDayEntities() {
        return trendDayEntities;
    }

    public void setTrendDayEntities(List<TrendDayTo> trendDayEntities) {
        this.trendDayEntities = trendDayEntities;
    }
}
