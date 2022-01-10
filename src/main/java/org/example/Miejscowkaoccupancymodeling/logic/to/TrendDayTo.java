package org.example.Miejscowkaoccupancymodeling.logic.to;

import java.util.List;

public class TrendDayTo {

    private int day;

    private List<TrendHourTo> trendHourEntities;

    public TrendDayTo() {
    }

    public TrendDayTo(int day, List<TrendHourTo> trendHourEntities) {
        this.day = day;
        this.trendHourEntities = trendHourEntities;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<TrendHourTo> getTrendHourEntities() {
        return trendHourEntities;
    }

    public void setTrendHourEntities(List<TrendHourTo> trendHourEntities) {
        this.trendHourEntities = trendHourEntities;
    }
}
