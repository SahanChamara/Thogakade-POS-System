package controller.order;

import model.Customer;
import model.Item;

import java.util.List;

public interface OrderService {
    String getOrderId();
    List<String> getCustomerId();
    Customer searchCustomerName(String customerId);
    List<Item> getItemCode();


}
