package br.com.beautique.query.services;

import br.com.beautique.query.dtos.customers.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> listAllCustomers();

    List<CustomerDTO> listByNameLikeIgnoreCaseCustomers(String name);

    List<CustomerDTO> listByEmailLikeIgnoreCaseCustomers(String email);
}
