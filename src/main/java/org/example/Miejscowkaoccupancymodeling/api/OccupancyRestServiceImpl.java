package org.example.Miejscowkaoccupancymodeling.api;

import org.example.Miejscowkaoccupancymodeling.exception.EntityAlreadyExistsException;
import org.example.Miejscowkaoccupancymodeling.logic.impl.OccupancyLogic;
import org.example.Miejscowkaoccupancymodeling.logic.to.OccupancyTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RestController
public class OccupancyRestServiceImpl implements OccupancyRestService {

    @Inject
    private OccupancyLogic occupancyLogic;

    @Override
    public ResponseEntity<OccupancyTo> createOccupancy(Long placeId, Integer numberOfPeople, LocalDateTime time) {
        try {
            return ResponseEntity
                    .created(new URI("/place/" + placeId + "/occupancy"))
                    .body(occupancyLogic.createOccupancy(placeId, numberOfPeople, time).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        } catch (
                EntityAlreadyExistsException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
