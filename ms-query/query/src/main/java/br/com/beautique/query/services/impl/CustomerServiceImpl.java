package br.com.beautique.query.services.impl;

import br.com.beautique.query.dtos.customers.CustomerDTO;
import br.com.beautique.query.repositories.ICustomerRepository;
import br.com.beautique.query.services.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> listAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers!");
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCaseCustomers(String name) {
        try {
            return customerRepository.findByNameLikeIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers by name!"+ e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCaseCustomers(String email) {
        try {
            return customerRepository.findByNameLikeIgnoreCase(email);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers by email!");
        }
    }
}
