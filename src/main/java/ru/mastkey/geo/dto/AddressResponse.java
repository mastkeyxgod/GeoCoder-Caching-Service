package ru.mastkey.geo.dto;

public class AddressResponse {
    private String address;

    public AddressResponse(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
