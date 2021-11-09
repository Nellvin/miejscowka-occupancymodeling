package org.example.Miejscowkaoccupancymodeling.api;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.Miejscowkaoccupancymodeling.model.OccupancyTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public interface OccupancyRestService {

    @ApiOperation(value = "Create occupancy",
            tags = {"occupancy"},
            response = OccupancyTo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "/place/{placeId}/occupancy",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OccupancyTo> createOccupancy(@PathVariable Long placeId, @RequestParam Integer numberOfPeople, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time);

}
