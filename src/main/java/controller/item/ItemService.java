package controller.item;

import model.Item;

import java.util.List;

public interface ItemService {
    boolean addItem(Item item);
    List<Item> getAllItems();
    String generateId();
    boolean deleteItem(Item item);
}
