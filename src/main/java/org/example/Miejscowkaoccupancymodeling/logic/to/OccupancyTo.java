package org.example.Miejscowkaoccupancymodeling.logic.to;

import com.sun.istack.NotNull;

import java.util.Objects;

public class OccupancyTo {

    @NotNull
    private int number_of_people;

    private int percentage_occupancy;

    public OccupancyTo() {
    }

    public OccupancyTo(int number_of_people, int percentage_occupancy) {
        this.number_of_people = number_of_people;
        this.percentage_occupancy = percentage_occupancy;
    }

    public int getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(int number_of_people) {
        this.number_of_people = number_of_people;
    }

    public int getPercentage_occupancy() {
        return percentage_occupancy;
    }

    public void setPercentage_occupancy(int percentage_occupancy) {
        this.percentage_occupancy = percentage_occupancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupancyTo that = (OccupancyTo) o;
        return number_of_people == that.number_of_people &&
                percentage_occupancy == that.percentage_occupancy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number_of_people, percentage_occupancy);
    }
}
