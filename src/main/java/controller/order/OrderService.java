package controller.order;

import model.Customer;
import model.Item;
import model.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    String getOrderId();
    List<String> getCustomerId();
    Customer searchCustomerName(String customerId);
    List<Item> getItemCode();
    List<Item> loadItemDetails(String itemId);
    boolean placeOrder(Orders orders) throws SQLException;
}
