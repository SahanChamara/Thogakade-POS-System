package controller.item;

import dbconnection.DBConnection;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {
    private static ItemController instance;

    private ItemController() {
    }

    public static ItemController getInstance() {
        return instance != null ? instance : new ItemController();
    }

    @Override
    public boolean addItem(Item item) {
        try {
            PreparedStatement prepaeStm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO item VALUES(?,?,?,?)");
            prepaeStm.setString(1, item.getCode());
            prepaeStm.setString(2, item.getDescription());
            prepaeStm.setDouble(3, item.getUnitPrice());
            prepaeStm.setInt(4, item.getQtyOnHand());
            return prepaeStm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT *" + " FROM item");
            while (rst.next()) {
                allItems.add((new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4))));
            }
            return allItems;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String generateId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT code FROM item ORDER BY code DESC LIMIT 1");
            return rst.next()?rst.getString(1):"P001";
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean deleteItem(Item item) {
        try {
            return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM item WHERE code='"+item.getCode()+"'")>0;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Item> searchItem(String code) {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT *" + " FROM item WHERE code='"+code+"'");
            if(rst.next()){
                itemArrayList.add(new Item(null,rst.getString(2),rst.getDouble(3),rst.getInt(4)));
            }
            return itemArrayList;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean updateItem(Item item) {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE item SET description=?, unitPrice=?,qtyOnHand=? WHERE code=?");
            prepareStm.setString(1,item.getDescription());
            prepareStm.setDouble(2,item.getUnitPrice());
            prepareStm.setInt(3,item.getQtyOnHand());
            prepareStm.setString(4,item.getCode());
            return prepareStm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
