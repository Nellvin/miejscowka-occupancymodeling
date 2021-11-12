package org.example.Miejscowkaoccupancymodeling.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.Miejscowkaoccupancymodeling.logic.to.TrendTo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TrendRestService {

    @ApiOperation(value = "Get trend",
            tags = {"trend"},
            response = TrendTo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @GetMapping(value = "/trend/{placeId}/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TrendTo> getTrend(@PathVariable Long placeId);

}
