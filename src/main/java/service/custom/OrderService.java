package service.custom;

import model.Customer;
import model.Item;
import model.Orders;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends SuperService {
    String getOrderId();
    List<String> getCustomerId();
    Customer searchCustomerName(String customerId);
    List<Item> getItemCode();
    List<Item> loadItemDetails(String itemId);
    boolean placeOrder(Orders orders) throws SQLException;
}
