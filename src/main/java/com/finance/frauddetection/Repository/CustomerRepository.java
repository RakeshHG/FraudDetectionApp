// CustomerRepository.java (Update your existing repository)
package com.finance.frauddetection.Repository;

import com.finance.frauddetection.Model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public CustomerRepository(){
        customers.add(new Customer(1, "Rahul Sharma", "ACC1001", "India"));
        customers.add(new Customer(2, "Priya Verma", "ACC1002", "Japan"));
        customers.add(new Customer(3, "John Smith", "ACC1003", "USA"));
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public Customer getCustomerById(int id){
        return customers.stream().filter(customer -> customer.getId() == id)
                .findFirst().orElse(null);
    }

    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public boolean update(int id, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.set(i, updatedCustomer);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(int id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }
}