package com.amir.customerapi.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE s.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

    @Query("SELECT s FROM Customer s WHERE s.email = ?1 AND s.id != ?2")
    Optional<Customer> findByEmailAndIdNot(String email, Long id);

}
