package org.app.projectpharmacy.services;

import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.repository.CustomerRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CustomerService {
    private CustomerRepository customerReporitory;

    public CustomerService() throws SQLException {
        this.customerReporitory = new CustomerRepository();
    }

    public List<Customer> fetchAllRecord() throws SQLException {
        return customerReporitory.getMany(null);
    }

    public Customer create(String name, String phoneNumber, String emailAddress, String description) throws SQLException {
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                name,
                phoneNumber,
                emailAddress,
                description,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        customerReporitory.create(customer);
        return customer;
    }

    public List<Customer> findCustomerByName(String name) throws SQLException {
        return customerReporitory.getByName(name);
    }
}
