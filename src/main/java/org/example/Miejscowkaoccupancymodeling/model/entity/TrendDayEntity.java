package org.example.Miejscowkaoccupancymodeling.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<TrendHourEntity> trendHourEntities;

    public TrendDayEntity() {
        this.trendHourEntities = new HashSet<>();
    }

    public TrendDayEntity(TrendEntity trend, int day, int averageSum, int dataCounter) {
        this.trend = trend;
        this.day = day;
        this.trendHourEntities = new HashSet<>();
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

    public Set<TrendHourEntity> getTrendHourEntities() {
        return trendHourEntities;
    }

    public void setTrendDayEntities(Set<TrendHourEntity> trendHourEntities) {
        this.trendHourEntities = trendHourEntities;
    }

    public boolean addTrendHour(TrendHourEntity trendHour) {
        return trendHourEntities.add(trendHour);
    }
}
