package org.example.Miejscowkaoccupancymodeling.logic.to;

public class TrendHourTo {

    private int hour;
    private int average;

    public TrendHourTo() {
    }

    public TrendHourTo(int hour, int average) {
        this.hour = hour;
        this.average = average;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
