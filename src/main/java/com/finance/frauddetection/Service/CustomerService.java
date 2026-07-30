// CustomerService.java
package com.finance.frauddetection.Service;

import com.finance.frauddetection.Model.Customer;
import com.finance.frauddetection.Repository.CustomerRepository;
import com.finance.frauddetection.Repository.SQLCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private SQLCustomerRepository customerRepository;

    public CustomerService(SQLCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean updateCustomer(int id, Customer customer) {
        customer.setId(id);
        return customerRepository.update(id, customer);
    }

    public boolean deleteCustomer(int id) {
        return customerRepository.deleteById(id);
    }
}