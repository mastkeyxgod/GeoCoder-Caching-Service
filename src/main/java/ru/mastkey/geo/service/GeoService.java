package ru.mastkey.geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.geo.dto.AddressRequest;
import ru.mastkey.geo.dto.AddressResponse;
import ru.mastkey.geo.dto.CoordinatesRequest;
import ru.mastkey.geo.dto.CoordinatesResponse;
import ru.mastkey.geo.util.CacheDBConnector;
import ru.mastkey.geo.util.GeoAPIConnector;

@Service
public class GeoService {

    private final GeoAPIConnector geoAPIConnector;

    private final CacheDBConnector cacheBDConnector;

    @Autowired
    public GeoService(GeoAPIConnector geoAPIConnector, CacheDBConnector cacheBDConnector) {
        this.geoAPIConnector = geoAPIConnector;
        this.cacheBDConnector = cacheBDConnector;
    }

    public AddressResponse getAddressByCoordinates(CoordinatesRequest coordinatesRequest) {

        String coordinates = coordinatesRequest.getLatitude() + "," + coordinatesRequest.getLongitude();
        String addressByCache = cacheBDConnector.getValueByKey(coordinates);

        if (addressByCache == null) {
            String addressByYandexAPI = geoAPIConnector.getAddressByCoordinates(coordinatesRequest.getLatitude(),
                    coordinatesRequest.getLongitude());
            saveValueToCache(coordinates, addressByYandexAPI);
            return new AddressResponse(addressByYandexAPI);
        }else {
            return new AddressResponse(addressByCache);
        }
    }

    public CoordinatesResponse getCoordinatesByAddress(AddressRequest addressRequest) {
        String address = addressRequest.getAddress();
        String coordinatesByCache = cacheBDConnector.getValueByKey(address);
        if (coordinatesByCache == null) {
            String[] coordinatesByYandexAPI = geoAPIConnector
                    .getCoordinatesByAddress(addressRequest.getAddress()).split(" ");
            String value = coordinatesByYandexAPI[0] + "," + coordinatesByYandexAPI[1];
            saveValueToCache(address, value);
            Double latitude = Double.valueOf(coordinatesByYandexAPI[0]);
            Double longitude = Double.valueOf(coordinatesByYandexAPI[1]);
            return new CoordinatesResponse(latitude, longitude);
        } else {
            String[] coordinates = coordinatesByCache.split(",");
            Double latitude = Double.valueOf(coordinates[0]);
            Double longitude = Double.valueOf(coordinates[1]);
            return new CoordinatesResponse(latitude, longitude);
        }
    }

    private void saveValueToCache(String key, String value) {
        cacheBDConnector.saveValueByKey(key, value);
    }
}
