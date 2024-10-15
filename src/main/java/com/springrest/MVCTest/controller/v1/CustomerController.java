package com.springrest.MVCTest.controller.v1;

import com.springrest.MVCTest.api.v1.model.CustomerDTO;
import com.springrest.MVCTest.services.CategoryService;
import com.springrest.MVCTest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService categoryService) {
        this.customerService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getListCustomer() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
}
