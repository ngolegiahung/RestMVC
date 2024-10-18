package com.springrest.MVCTest.services;

import com.springrest.MVCTest.api.v1.mapper.CustomerMapper;
import com.springrest.MVCTest.api.v1.model.CustomerDTO;
import com.springrest.MVCTest.domain.Customer;
import com.springrest.MVCTest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl("api/v1/customer/" + id);
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveCustomer(customer);
    }

    private CustomerDTO saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        returnCustomerDTO.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnCustomerDTO;
    }
}
