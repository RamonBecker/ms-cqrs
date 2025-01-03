package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.repositories.ICustomerRepository;
import br.com.beautique.ms_sync.services.ICustomerService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void save(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            customerRepository.save(customer);

        } catch (Exception e) {
            SyncLogger.error("Error saving customer: "+ e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
