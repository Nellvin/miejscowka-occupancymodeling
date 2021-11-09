package org.example.Miejscowkaoccupancymodeling.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "OCCUPANCY")
public class OccupancyEntity {

    @EmbeddedId
    private OccupancyId id;

    @NotNull
    @Column(name = "NUMBER_OF_PEOPLE", nullable = false)
    private int number_of_people;

    @Column(name = "PERCENTAGE")
    private int percentage_occupancy;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "TREND_ID", nullable = true, referencedColumnName = "id")
    private TrendEntity trend;

    public OccupancyEntity() {
    }

    public OccupancyEntity(Long placeId, Integer numberOfPeople, LocalDateTime time) {
        this.number_of_people = numberOfPeople;
        this.id = new OccupancyId(placeId, time);
    }

    public OccupancyId getId() {
        return id;
    }

    public void setId(OccupancyId id) {
        this.id = id;
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
        OccupancyEntity that = (OccupancyEntity) o;
        return number_of_people == that.number_of_people && percentage_occupancy == that.percentage_occupancy && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number_of_people, percentage_occupancy);
    }
}
