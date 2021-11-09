package org.example.Miejscowkaoccupancymodeling.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TREND")
public class TrendEntity {

    public TrendEntity() {
    }

    public TrendEntity(Set<OccupancyEntity> hourOccupancies) {
        this.id = hourOccupancies.stream().findFirst().map(OccupancyEntity::getId).map(OccupancyId::getPlaceId).orElse(-1L);
        this.hourOccupancies = hourOccupancies;
    }

    @Id
    private long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trend", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<OccupancyEntity> hourOccupancies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<OccupancyEntity> getHourOccupancies() {
        return hourOccupancies;
    }

    public void setHourOccupancies(Set<OccupancyEntity> hourOccupancies) {
        this.hourOccupancies = hourOccupancies;
    }
}
