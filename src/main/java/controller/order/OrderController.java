package controller.order;

import controller.customer.CustomerController;
import controller.item.ItemController;
import dbconnection.DBConnection;
import model.Customer;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController implements OrderService{
    private static OrderController instance;

    private OrderController() {
    }
    public static OrderController getInstance(){
        return instance!=null?instance:new OrderController();
    }

    @Override
    public String getOrderId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
            return rst.next()?rst.getString(1):"D001";
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<String> getCustomerId() {
        ArrayList<String> customerIdList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM customer");
            while(rst.next()){
                customerIdList.add(rst.getString("id"));
            }
            return customerIdList;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Customer searchCustomerName(String customerId) {
        return CustomerController.getInstance().searchCustomer(customerId);
    }

    @Override
    public List<Item> getItemCode() {
        return ItemController.getInstance().getAllItems();
    }
}
