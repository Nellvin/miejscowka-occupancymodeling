package org.example.Miejscowkaoccupancymodeling.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<TrendDayEntity> trendDayEntities;

    public TrendEntity() {
        this.trendDayEntities = new ArrayList<>();
    }

    public TrendEntity(Long placeId) {
        this.placeId = placeId;
        this.trendDayEntities = new ArrayList<>();

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

    public List<TrendDayEntity> getTrendDayEntities() {
        return trendDayEntities;
    }

    public void setTrendDayEntities(List<TrendDayEntity> trendDayEntities) {
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
