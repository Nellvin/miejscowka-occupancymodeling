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
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public Optional<TrendTo> findTrend(long placeId) throws EntityDoesNotExistException {
        TrendEntity trend = trendDao.getById(placeId);
//        if (trend.getPlaceId() != null && !trend.getTrendDayEntities().isEmpty()) {

//        } else {
        trend = createTrend(placeId);
//        }
        return Optional.of(trendMapper.toTrendTo(trend));
    }

    private TrendEntity createTrend(long placeId) {
        Optional<Set<OccupancyEntity>> occupancies = occupancyDao.findByIdPlaceId(placeId);
        TrendEntity trend = countTrend(occupancies.get(), placeId);
        return trend;
    }

    private TrendEntity countTrend(Set<OccupancyEntity> occupancy, long placeId) {
        Assert.notEmpty(occupancy);
        TrendEntity trend = new TrendEntity(placeId);
        DAYS.forEach(day -> {
            Set<OccupancyEntity> occupancyFromDay = occupancy.stream().filter(occ -> occ.getId().getTimeId().getDayOfWeek().getValue() == day).collect(Collectors.toSet());
            TrendDayEntity dayTrend = countTrendDays(occupancyFromDay, day);
            if (dayTrend != null) {
                trend.addTrendDay(dayTrend);
            }
        });
        if (trend.getTrendDayEntities().isEmpty())
            return null;
        trendDao.saveAndFlush(trend);
        return trend;
    }

    private TrendDayEntity countTrendDays(Set<OccupancyEntity> occupancyFromDay, int day) {
        TrendDayEntity trendDay = new TrendDayEntity();
        trendDay.setDay(day);
        HOURS.forEach(hour -> {
            Set<OccupancyEntity> occupancyFromDayHour = occupancyFromDay.stream().filter(occ -> occ.getId().getTimeId().getHour() == hour).collect(Collectors.toSet());
            TrendHourEntity trendHour = countTrendHours(occupancyFromDayHour, hour);
            if (trendHour != null) {
                trendDay.addTrendHour(trendHour);
            }
        });
        if (trendDay.getTrendHourEntities().isEmpty())
            return null;
        trendDayDao.save(trendDay);
        return trendDay;
    }

    private TrendHourEntity countTrendHours(Set<OccupancyEntity> occupancy, int hour) {
        if (occupancy.isEmpty()) {
            return null;
        }
        int occupancySum = occupancy.stream().map(OccupancyEntity::getNumber_of_people).reduce(0, Integer::sum);
        int occupancyEntries = occupancy.size();
        TrendHourEntity trendHour = new TrendHourEntity(null, hour, occupancySum / occupancyEntries, occupancySum, occupancy.size());
        if (trendHour.getAverage() > 0) {
            trendHourDao.save(trendHour);
            return trendHour;
        }
        return null;
    }
}
