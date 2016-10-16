package jtk.springboot.components;

import jtk.springboot.entities.Customer;
import jtk.springboot.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by jubin on 25/9/16.
 */
@Component
@Lazy
public class CustomerRegistrar {

    private CustomerRepository customerRespository;

    @Autowired
    public CustomerRegistrar(CustomerRepository customerRespository){
        this.customerRespository = customerRespository;
    }

    public Customer register(Customer customer){
        Optional<Customer> existingCustomer = customerRespository.findByName(customer.getName());
        if (existingCustomer.isPresent()){
            throw new RuntimeException("is already exists");
        } else {
            customerRespository.save(customer);
        }
        return customer;
    }
}
