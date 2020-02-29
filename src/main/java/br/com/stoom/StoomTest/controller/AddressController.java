package br.com.stoom.StoomTest.controller;

import br.com.stoom.StoomTest.domain.Address;
import br.com.stoom.StoomTest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PutMapping
    public ResponseEntity<?> putAddress(@RequestBody @Valid Address address) {
        addressService.upsert(address);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> patchAddress(@RequestBody @Valid Address address) {
        addressService.upsert(address);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        addressService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id) {
        Optional<Address> optionalAddress = addressService.findById(id);
        return optionalAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
