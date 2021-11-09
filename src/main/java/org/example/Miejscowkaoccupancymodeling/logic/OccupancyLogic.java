package org.example.Miejscowkaoccupancymodeling.logic;

import org.example.Miejscowkaoccupancymodeling.exception.EntityAlreadyExistsException;
import org.example.Miejscowkaoccupancymodeling.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OccupancyLogic {

    private static final Logger LOG = LoggerFactory.getLogger(OccupancyLogic.class);

    @Inject
    private OccupancyDao occupancyDao;

    @Inject
    private OccupancyMapper occupancyMapper;

    public Optional<OccupancyTo> createOccupancy(Long placeId, Integer numberOfPeople, LocalDateTime time) throws EntityAlreadyExistsException {
        OccupancyId occupancyId = new OccupancyId(placeId, time);
        if (occupancyDao.existsOccupancyEntityById(occupancyId)) {
            LOG.error("Occupancy with similar primary key was found", occupancyId);
            throw new EntityAlreadyExistsException("Occupancy with this id already exits");
        }
        OccupancyEntity occupancyEntity = new OccupancyEntity(placeId, numberOfPeople, time);
        occupancyDao.save(occupancyEntity);
        LOG.info("Created new Occupancy", occupancyEntity);

        return Optional.of(occupancyMapper.toOccupancyTo(occupancyEntity));
    }
}
