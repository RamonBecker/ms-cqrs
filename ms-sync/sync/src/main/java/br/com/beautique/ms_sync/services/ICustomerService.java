package br.com.beautique.ms_sync.services;

import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface ICustomerService {

    void save(CustomerDTO customer);
}
