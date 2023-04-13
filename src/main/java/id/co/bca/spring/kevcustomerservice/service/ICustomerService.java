package id.co.bca.spring.kevcustomerservice.service;

import id.co.bca.spring.kevcustomerservice.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService {
    Customer insertCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(int id);

    List<Customer> retrieveAllCustomer();

    Customer retrieveCustomerById(int id);

}
