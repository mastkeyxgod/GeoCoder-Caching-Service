package ru.mastkey.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.geo.dto.AddressRequest;
import ru.mastkey.geo.dto.AddressResponse;
import ru.mastkey.geo.dto.CoordinatesRequest;
import ru.mastkey.geo.dto.CoordinatesResponse;
import ru.mastkey.geo.service.GeoService;

@RestController
@RequestMapping("/geocoding")
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/coordinates")
    public AddressResponse getAddress(@RequestBody CoordinatesRequest coordinatesRequest) {
        return geoService.getAddressByCoordinates(coordinatesRequest);
    }

    @GetMapping("/address")
    public CoordinatesResponse getCoordinates(@RequestBody AddressRequest addressRequest) {
        return geoService.getCoordinatesByAddress(addressRequest);
    }
}
