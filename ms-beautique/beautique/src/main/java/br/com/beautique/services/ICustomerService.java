package br.com.beautique.services;

import br.com.beautique.dtos.CustomerDTO;

public interface ICustomerService {

    CustomerDTO create(CustomerDTO customer);
    void deleteById(Long id);
    CustomerDTO update(CustomerDTO customer);

}
