package br.com.beautique.controllers;

import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping()
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }


}
