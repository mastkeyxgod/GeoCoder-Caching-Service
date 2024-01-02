package ru.mastkey.geo.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class YandexAPI implements GeoAPIConnector{
    @Value("${yandex.base_url}")
    private  String BASE_URL;
    @Value("${yandex.key}")
    private String MY_KEY;

    @Autowired
    private final RestTemplate restTemplate;

    public YandexAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAddressByCoordinates(Double latitude, Double longitude) {
        String coordinates = latitude + "," + longitude;
        String response = getResponse(coordinates);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
           jsonNode = objectMapper.readTree(response);
           Thread.sleep(5000);

            return jsonNode
                    .path("response")
                    .path("GeoObjectCollection")
                    .path("featureMember")
                    .path(0)
                    .path("GeoObject")
                    .path("metaDataProperty")
                    .path("GeocoderMetaData")
                    .path("text")
                    .asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect YandexAPI");
        }
    }

    @Override
    public String getCoordinatesByAddress(String address) {
        address = address.replaceAll(" ", "+");
        String response = getResponse(address);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
            jsonNode = objectMapper.readTree(response);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String posValue = jsonNode
                .path("response")
                .path("GeoObjectCollection")
                .path("featureMember")
                .path(0)
                .path("GeoObject")
                .path("Point")
                .path("pos")
                .asText();

        return posValue;
    }

    private String getResponse(String param) {
        String response = restTemplate
                .getForObject(BASE_URL + "?apikey=" + MY_KEY + "&geocode=" + param + "&format=json",
                        String.class);
        return response;
    }

}
