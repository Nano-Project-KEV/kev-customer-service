package id.co.bca.spring.kevcustomerservice.repository;

import id.co.bca.spring.kevcustomerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
