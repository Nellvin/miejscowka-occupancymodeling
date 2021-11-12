package org.example.Miejscowkaoccupancymodeling.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "OCCUPANCY")
public class OccupancyEntity {

    @EmbeddedId
    private OccupancyId id;

    @Column(name = "IS_ACTIVE")
    private boolean active;

    @NotNull
    @Column(name = "NUMBER_OF_PEOPLE", nullable = false)
    private int number_of_people;

    @Column(name = "PERCENTAGE")
    private int percentage_occupancy;


    public OccupancyEntity() {
        this.active = true;
    }

    public OccupancyEntity(Long placeId, Integer numberOfPeople, LocalDateTime time) {
        super();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
