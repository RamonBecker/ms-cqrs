package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ICustomerRepository extends MongoRepository<CustomerDTO, Long> {

    @Query("{ 'name' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByNameLikeIgnoreCase(String name);

    @Query("{ 'email' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByEmailLikeIgnoreCase(String email);
}
