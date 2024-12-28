package br.com.beautique.controllers;

import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping()
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok(customerService.update(customer));
    }
}
