package org.example.Miejscowkaoccupancymodeling.logic.impl;

import org.example.Miejscowkaoccupancymodeling.exception.EntityDoesNotExistException;
import org.example.Miejscowkaoccupancymodeling.logic.mapper.TrendMapper;
import org.example.Miejscowkaoccupancymodeling.logic.to.TrendTo;
import org.example.Miejscowkaoccupancymodeling.model.dao.OccupancyDao;
import org.example.Miejscowkaoccupancymodeling.model.dao.TrendDao;
import org.example.Miejscowkaoccupancymodeling.model.dao.TrendDayDao;
import org.example.Miejscowkaoccupancymodeling.model.dao.TrendHourDao;
import org.example.Miejscowkaoccupancymodeling.model.entity.OccupancyEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendDayEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendEntity;
import org.example.Miejscowkaoccupancymodeling.model.entity.TrendHourEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrendLogic {

    private final List<Integer> DAYS = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    private final List<Integer> HOURS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

    @Inject
    private OccupancyDao occupancyDao;

    @Inject
    private TrendDao trendDao;

    @Inject
    private TrendDayDao trendDayDao;

    @Inject
    private TrendHourDao trendHourDao;

    @Inject
    private TrendMapper trendMapper;

    @Transactional
    public Optional<TrendTo> findTrend(Long placeId) throws EntityDoesNotExistException {
        TrendEntity trend;
        Optional<Set<OccupancyEntity>> occupancies = occupancyDao.findByIdPlaceId(placeId);
        if (trendDao.existsByPlaceId(placeId)) {
            trend = trendDao.getByPlaceId(placeId);
            updateTrend(trend, occupancies);
        } else {
            trend = createTrend(placeId, occupancies);
        }
        return Optional.ofNullable(trendMapper.toTrendTo(trend));
    }

    private void updateTrend(TrendEntity trend, Optional<Set<OccupancyEntity>> occupancies) {
        DAYS.forEach(day -> {
            Set<OccupancyEntity> occupancyFromDay = occupancies.orElse(Collections.emptySet()).stream().filter(occ -> occ.getId().getTimeId().getDayOfWeek().getValue() == day).collect(Collectors.toSet());
            if (trendDayDao.existsByDayAndTrend(day, trend)) {
                updateTrendDays(occupancyFromDay, day, trend);
            } else {
                TrendDayEntity dayTrend = createTrendDays(occupancyFromDay, day, trend);
                if (dayTrend != null) {
                    trend.addTrendDay(dayTrend);
                }
            }
        });
    }

    private TrendDayEntity updateTrendDays(Set<OccupancyEntity> occupancyFromDay, Integer day, TrendEntity trend) {
        TrendDayEntity trendDay = trendDayDao.findByDayAndTrend(day, trend).orElse(null);
        trendDay.setDay(day);
        HOURS.forEach(hour -> {
            Set<OccupancyEntity> occupancyFromDayHour = occupancyFromDay.stream().filter(occ -> occ.getId().getTimeId().getHour() == hour).collect(Collectors.toSet());
            if (trendHourDao.existsByHourAndTrendDay(hour, trendDay)) {
                updateTrendHours(occupancyFromDayHour, hour, trendDay);
            } else {
                TrendHourEntity hourTrend = createTrendHours(occupancyFromDayHour, day, trendDay);
                if (hourTrend != null) {
                    trendDay.addTrendHour(hourTrend);
                }
            }
        });
        return trendDay;
    }

    private TrendHourEntity updateTrendHours(Set<OccupancyEntity> occupancyFromDayHour, Integer hour, TrendDayEntity trendDay) {
        if (occupancyFromDayHour.isEmpty()) {
            return null;
        }
        TrendHourEntity trendHour = trendHourDao.findByHourAndTrendDay(hour, trendDay).orElse(null);
        int occupancySum = trendHour.getAverageSum() + occupancyFromDayHour.stream().map(OccupancyEntity::getNumber_of_people).reduce(0, Integer::sum);
        int occupancyEntries = trendHour.getDataCounter() + occupancyFromDayHour.size();
        trendHour.setAverage(occupancySum / occupancyEntries);
        trendHour.setAverageSum(occupancySum);
        trendHour.setDataCounter(occupancyEntries);
        trendHourDao.save(trendHour);
        return trendHour;
    }

    private TrendEntity createTrend(long placeId, Optional<Set<OccupancyEntity>> occupancies) {
        if (occupancies.get().isEmpty())
            return null;
        TrendEntity trend = createTrend(occupancies.get(), placeId);
        return trend;
    }

    private TrendEntity createTrend(Set<OccupancyEntity> occupancy, long placeId) {
        TrendEntity trend = new TrendEntity(placeId);
        DAYS.forEach(day -> {
            Set<OccupancyEntity> occupancyFromDay = occupancy.stream().filter(occ -> occ.getId().getTimeId().getDayOfWeek().getValue() == day).collect(Collectors.toSet());
            TrendDayEntity dayTrend = createTrendDays(occupancyFromDay, day, trend);
            if (dayTrend != null) {
                trend.addTrendDay(dayTrend);
            }
        });
        if (trend.getTrendDayEntities().isEmpty())
            return null;
        trendDao.saveAndFlush(trend);
        return trend;
    }

    private TrendDayEntity createTrendDays(Set<OccupancyEntity> occupancyFromDay, int day, TrendEntity trend) {
        TrendDayEntity trendDay = new TrendDayEntity();
        trendDay.setDay(day);
        HOURS.forEach(hour -> {
            Set<OccupancyEntity> occupancyFromDayHour = occupancyFromDay.stream().filter(occ -> occ.getId().getTimeId().getHour() == hour).collect(Collectors.toSet());
            TrendHourEntity trendHour = createTrendHours(occupancyFromDayHour, hour, trendDay);
            if (trendHour != null) {
                trendDay.addTrendHour(trendHour);
            }
        });
        if (trendDay.getTrendHourEntities().isEmpty())
            return null;
        trendDay.setTrend(trend);
        trendDayDao.save(trendDay);
        return trendDay;
    }

    private TrendHourEntity createTrendHours(Set<OccupancyEntity> occupancy, int hour, TrendDayEntity trendDay) {
        if (occupancy.isEmpty()) {
            return null;
        }
        int occupancySum = occupancy.stream().map(OccupancyEntity::getNumber_of_people).reduce(0, Integer::sum);
        int occupancyEntries = occupancy.size();
        TrendHourEntity trendHour = new TrendHourEntity(null, hour, occupancySum / occupancyEntries, occupancySum, occupancy.size());
        if (trendHour.getAverage() > 0) {
            trendHour.setTrendDay(trendDay);
            trendHourDao.save(trendHour);
            return trendHour;
        }
        return null;
    }
}
