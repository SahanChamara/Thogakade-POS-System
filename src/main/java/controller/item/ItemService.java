package controller.item;

import model.Item;
import model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public interface ItemService {
    boolean addItem(Item item);
    List<Item> getAllItems();
    String generateId();
    boolean deleteItem(Item item);
    List<Item>searchItem(String code);
    boolean updateItem(Item item);
    boolean updateStock(ArrayList<OrderDetail> orderDetails);
    boolean updateStock(OrderDetail orderDetail);
}
