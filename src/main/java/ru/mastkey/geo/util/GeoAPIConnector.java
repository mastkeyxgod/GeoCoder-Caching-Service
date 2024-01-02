package ru.mastkey.geo.util;

public interface GeoAPIConnector {
    public String getAddressByCoordinates(Double latitude, Double longitude);

    public String getCoordinatesByAddress(String address);
}
