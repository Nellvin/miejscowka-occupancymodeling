package org.example.Miejscowkaoccupancymodeling.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TREND")
public class TrendEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Id
    @Column(name = "PLACE_ID")
    private Long placeId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trend", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<TrendDayEntity> trendDayEntities;

    public TrendEntity() {
        this.trendDayEntities = new HashSet<>();
    }

    public TrendEntity(Long placeId) {
        this.placeId = placeId;
        this.trendDayEntities = new HashSet<>();

    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Set<TrendDayEntity> getTrendDayEntities() {
        return trendDayEntities;
    }

    public void setTrendDayEntities(Set<TrendDayEntity> trendDayEntities) {
        this.trendDayEntities = trendDayEntities;
    }

    public boolean addTrendDay(TrendDayEntity dayTrend) {
        return trendDayEntities.add(dayTrend);
    }

//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public Set<OccupancyEntity> getHourOccupancies() {
//        return hourOccupancies;
//    }
//
//    public void setHourOccupancies(Set<OccupancyEntity> hourOccupancies) {
//        this.hourOccupancies = hourOccupancies;
//    }
}
