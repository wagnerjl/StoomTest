package br.com.stoom.StoomTest.client;

import br.com.stoom.StoomTest.domain.GeocodingResponse;
import br.com.stoom.StoomTest.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class GeocodingClient {

    private RestTemplate restTemplate;

    @Autowired
    public GeocodingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Location findLatLng(String address) {
        String url = this.getUrl(address);
        GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class, new HashMap<>());
        if(response != null && response.getResults().length > 0) {
            return response.getResults()[0].getGeometry().getLocation();
        }
        throw new RuntimeException("Error on retrieving latitude/longitude");
    }

    private String getUrl(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json" + "?address=" + address + "&key=" + "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc";
        return url.replace(" ", "+");
    }

}
