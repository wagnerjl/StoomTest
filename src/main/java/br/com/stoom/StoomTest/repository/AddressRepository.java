package br.com.stoom.StoomTest.repository;

import br.com.stoom.StoomTest.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, Integer> {
}
