package com.amir.customerapi.customer;

import com.amir.customerapi.exception.CustomerDoesNotExistException;
import com.amir.customerapi.exception.EmailAlreadyExistsException;
import com.amir.customerapi.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        return customerRepository
                .findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException =
                            new NotFoundException("customer with id " + id + " not found.");
                    return notFoundException;
                });
    }

    public void addNewCustomer(Customer customer){
        customerRepository.findCustomerByEmail(customer.getEmail())
                .ifPresent(existingCustomer -> {
                    throw new EmailAlreadyExistsException("email is taken.");
                });

        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException("customer with id " + id + " does not exist."));
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomer(Long id, String firstName, String lastName, String email, String password, LocalDate dob){
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException("customer with id " + id + " does not exist."));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName))
            customer.setFirstName(firstName);

        if (lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName))
            customer.setLastName(lastName);

        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            customerRepository.findByEmailAndIdNot(email, id)
                    .ifPresent(existingCustomer -> {
                        throw new EmailAlreadyExistsException("email is already taken.");
                    });
        }
        customer.setEmail(email);

        customer.setPassword(password != null && !password.isEmpty() ? password : customer.getPassword());

        customer.setDob(dob != null && !dob.equals(LocalDate.MIN) ? dob : customer.getDob());
    }

}
