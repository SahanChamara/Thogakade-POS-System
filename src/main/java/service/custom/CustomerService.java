package service.custom;

import model.Customer;
import service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    List<Customer> getAllCustomer();

    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String customerId);
    Customer searchCustomer(String customerId);
    String generateId();
}
