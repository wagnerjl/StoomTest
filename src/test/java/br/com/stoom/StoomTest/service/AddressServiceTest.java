package br.com.stoom.StoomTest.service;

import br.com.stoom.StoomTest.client.GeocodingClient;
import br.com.stoom.StoomTest.domain.Address;
import br.com.stoom.StoomTest.domain.Location;
import br.com.stoom.StoomTest.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private GeocodingClient geocodingClient;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testUpsert() {
        when(geocodingClient.findLatLng(anyString())).thenReturn(new Location(1.1,1.2));
        Address address = new Address();
        addressService.upsert(address);
        Mockito.verify(addressRepository).save(address);
        assertEquals(address.getLatitude(), "1.1");
        assertEquals(address.getLongitude(), "1.2");
    }

    @Test
    public void testDeleteById() {
        addressService.deleteById(1);
        verify(addressRepository).deleteById(1);
    }

    @Test
    public void testFindById() {
        Address address = new Address();
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        Optional<Address> result = addressService.findById(1);
        assertEquals(address, result.get());
    }
}