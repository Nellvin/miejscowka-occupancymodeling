package org.example.Miejscowkaoccupancymodeling.api;

import org.example.Miejscowkaoccupancymodeling.exception.EntityDoesNotExistException;
import org.example.Miejscowkaoccupancymodeling.logic.impl.TrendLogic;
import org.example.Miejscowkaoccupancymodeling.logic.to.TrendTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;

@RestController
public class TrendRestServiceImpl implements TrendRestService {

    @Inject
    private TrendLogic trendLogic;

    @Override
    public ResponseEntity<TrendTo> getTrend(Long placeId) {
        try {
            return ResponseEntity
                    .ok()
                    .body(trendLogic.findTrend(placeId).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        } catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
