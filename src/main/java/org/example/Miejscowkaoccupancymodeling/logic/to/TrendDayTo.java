package org.example.Miejscowkaoccupancymodeling.logic.to;

import java.util.Set;

public class TrendDayTo {

    private int day;

    private Set<TrendHourTo> trendHourEntities;

    public TrendDayTo() {
    }

    public TrendDayTo(int day, Set<TrendHourTo> trendHourEntities) {
        this.day = day;
        this.trendHourEntities = trendHourEntities;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Set<TrendHourTo> getTrendHourEntities() {
        return trendHourEntities;
    }

    public void setTrendHourEntities(Set<TrendHourTo> trendHourEntities) {
        this.trendHourEntities = trendHourEntities;
    }
}
