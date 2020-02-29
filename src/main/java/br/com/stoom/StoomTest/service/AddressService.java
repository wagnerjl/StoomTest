package br.com.stoom.StoomTest.service;

import br.com.stoom.StoomTest.client.GeocodingClient;
import br.com.stoom.StoomTest.domain.Address;
import br.com.stoom.StoomTest.domain.Location;
import br.com.stoom.StoomTest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private GeocodingClient geocodingClient;

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(GeocodingClient geocodingClient, AddressRepository addressRepository) {
        this.geocodingClient = geocodingClient;
        this.addressRepository = addressRepository;
    }

    public void upsert(Address address) {
        if(address.getLatitude() == null || address.getLongitude() == null) {
            Location location = geocodingClient.findLatLng(stringfyAddress(address));
            address.setLatitude(String.valueOf(location.getLat()));
            address.setLongitude(String.valueOf(location.getLng()));
        }
        addressRepository.save(address);
    }

    private String stringfyAddress(Address address) {
        return String.join(" ", address.getStreetName(), address.getNumber(), address.getCity(), address.getState());
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }

    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }
}
