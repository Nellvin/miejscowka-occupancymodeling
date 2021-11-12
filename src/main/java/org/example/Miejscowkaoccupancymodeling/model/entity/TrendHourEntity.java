package org.example.Miejscowkaoccupancymodeling.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "TREND_HOUR")
public class TrendHourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "TREND_DAY_ID")
    private TrendDayEntity trendDay;

    @Column(name = "HOUR")
    private int hour;

    @Column(name = "AVERAGE")
    private int average;

    @Column(name = "AVERAGE_SUM")
    private int averageSum;

    @Column(name = "DATA_COUNTER")
    private int dataCounter;

    public TrendHourEntity() {
    }

    public TrendHourEntity(TrendDayEntity trendDay, int hour, int average, int averageSum, int dataCounter) {
        this.trendDay = trendDay;
        this.hour = hour;
        this.average = average;
        this.averageSum = averageSum;
        this.dataCounter = dataCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrendDayEntity getTrendDay() {
        return trendDay;
    }

    public void setTrendDay(TrendDayEntity trendDay) {
        this.trendDay = trendDay;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getAverageSum() {
        return averageSum;
    }

    public void setAverageSum(int averageSum) {
        this.averageSum = averageSum;
    }

    public int getDataCounter() {
        return dataCounter;
    }

    public void setDataCounter(int dataCounter) {
        this.dataCounter = dataCounter;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrendHourEntity trendHour = (TrendHourEntity) o;
        return hour == trendHour.hour && Objects.equals(trendDay, trendHour.trendDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trendDay, hour);
    }
}
