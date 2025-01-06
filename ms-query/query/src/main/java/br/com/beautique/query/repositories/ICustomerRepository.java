package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepository extends MongoRepository<CustomerDTO, Long> {
}
