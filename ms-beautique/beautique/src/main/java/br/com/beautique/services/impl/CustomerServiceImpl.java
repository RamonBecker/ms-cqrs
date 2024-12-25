package br.com.beautique.services.impl;

import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.ICostumerRepository;
import br.com.beautique.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICostumerRepository costumerRepository;

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        return costumerRepository.save(customer);
    }
}
