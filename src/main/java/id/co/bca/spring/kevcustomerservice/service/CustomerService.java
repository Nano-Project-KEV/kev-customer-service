package id.co.bca.spring.kevcustomerservice.service;

import id.co.bca.spring.kevcustomerservice.model.Customer;
import id.co.bca.spring.kevcustomerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    @Qualifier("customerRepository")
    private CustomerRepository customerRepository;

    @Override
    public Customer insertCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer updateCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if(updateCustomer == null) {
            return null;
        }

        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setName(customer.getName());
        updateCustomer.setEmail(customer.getEmail());

        return customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> retrieveAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer retrieveCustomerById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        return result.orElse(null);
    }
}
