package br.com.beautique.services.impl;

import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.ICostumerRepository;
import br.com.beautique.services.IBrokerService;
import br.com.beautique.services.ICustomerService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICostumerRepository costumerRepository;

    @Autowired
    private IBrokerService brokerService;

    private final ConvertUtil<CustomerEntity, CustomerDTO> convertUtil = new ConvertUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        var customer = convertUtil.convertToSource(customerDTO);
        var newCustomer = costumerRepository.save(customer);

        sendCustomerToQueue(newCustomer);

        return convertUtil.convertToTarget(newCustomer);
    }

    @Override
    public void deleteById(Long id) {
        var findCustomer = costumerRepository.findById(id);

        if (findCustomer.isEmpty())
            throw new RuntimeException("Customer not found!");

        costumerRepository.delete(findCustomer.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customer) {
        var findCustomer = costumerRepository.findById(customer.getId());

        if (findCustomer.isEmpty())
            throw new RuntimeException("Customer not found!");

        var customerEntity = convertUtil.convertToSource(customer);

        customerEntity.setAppointments(findCustomer.get().getAppointments());
        customerEntity.setCreatedAt(findCustomer.get().getCreatedAt());

        var updateCustomer =costumerRepository.save(customerEntity);

        sendCustomerToQueue(updateCustomer);

        return convertUtil.convertToTarget(updateCustomer);
    }

    private void sendCustomerToQueue(CustomerEntity customer) {
        var customerDTO = CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();

        brokerService.send("customer", customerDTO);
    }

}
