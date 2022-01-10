package org.example.Miejscowkaoccupancymodeling.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "TREND_DAY")
public class TrendDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "TREND_ID")
    private TrendEntity trend;

    @Column(name = "DAY")
    private int day;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trendDay", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<TrendHourEntity> trendHourEntities;

    public TrendDayEntity() {
        this.trendHourEntities = new ArrayList<>();
    }

    public TrendDayEntity(TrendEntity trend, int day, int averageSum, int dataCounter) {
        this.trend = trend;
        this.day = day;
        this.trendHourEntities = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrendEntity getTrend() {
        return trend;
    }

    public void setTrend(TrendEntity trend) {
        this.trend = trend;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<TrendHourEntity> getTrendHourEntities() {
        return trendHourEntities;
    }

    public void setTrendDayEntities(List<TrendHourEntity> trendHourEntities) {
        this.trendHourEntities = trendHourEntities;
    }

    public boolean addTrendHour(TrendHourEntity trendHour) {
        return trendHourEntities.add(trendHour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrendDayEntity trendDay = (TrendDayEntity) o;
        return day == trendDay.day && trend.equals(trendDay.trend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trend, day);
    }
}
