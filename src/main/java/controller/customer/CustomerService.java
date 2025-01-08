package controller.customer;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String customerId);
    Customer searchCustomer(String customerId);
    String generateId();
}
