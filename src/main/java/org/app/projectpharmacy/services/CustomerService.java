package org.app.projectpharmacy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.repository.CustomerRepository;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import static org.app.projectpharmacy.utils.ObjectMapperConvention.toJson;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuditTrailService auditTrailService;

    public CustomerService() throws SQLException {
        this.auditTrailService = new AuditTrailService();
        this.customerRepository = new CustomerRepository();
    }

    public List<Customer> fetchAllRecord() throws SQLException {
        return customerRepository.getMany(null);
    }

    public Customer create(String name, String phoneNumber, String emailAddress, String description) throws SQLException, JsonProcessingException {
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                name,
                phoneNumber,
                emailAddress,
                description,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        customerRepository.create(customer);

        this.auditTrailService.create("Customer", "Create", toJson(customer));
        return customer;
    }

    public List<Customer> findCustomerByName(String name) throws SQLException {
        return customerRepository.getByName(name);
    }
}
