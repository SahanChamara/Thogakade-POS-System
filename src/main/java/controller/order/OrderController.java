package controller.order;

import controller.customer.CustomerController;
import controller.item.ItemController;
import controller.orderdetail.OrderDetailController;
import dbconnection.DBConnection;
import model.Customer;
import model.Item;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController implements OrderService {
    private static OrderController instance;

    private OrderController() {
    }

    public static OrderController getInstance() {
        return instance != null ? instance : new OrderController();
    }

    @Override
    public String getOrderId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
            return rst.next() ? rst.getString(1) : "D001";
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<String> getCustomerId() {
        ArrayList<String> customerIdList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM customer");
            while (rst.next()) {
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

    @Override
    public List<Item> loadItemDetails(String itemId) {
        return ItemController.getInstance().searchItem(itemId);
    }

    @Override
    public boolean placeOrder(Orders orders) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?)");
            pst.setString(1, orders.getId());
            pst.setString(2, orders.getDate());
            pst.setString(3, orders.getCustomerId());

            if (pst.executeUpdate() > 0 && OrderDetailController.getInstance().addOrderDetail(orders.getOrderDetailList()) && ItemController.getInstance().updateStock(orders.getOrderDetailList())) {
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }


}
