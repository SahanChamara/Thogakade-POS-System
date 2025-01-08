package controller.customer;

import dbconnection.DBConnection;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {
    private static CustomerController instance;

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        return instance == null ? instance = new CustomerController() : instance;
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM customer");
            while (rst.next()) {
                customerList.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            prepareStm.setObject(1, customer.getId());
            prepareStm.setObject(2, customer.getName());
            prepareStm.setObject(3, customer.getAddress());
            prepareStm.setObject(4, customer.getSalary());
            return prepareStm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE customer SET name=?,address=?,salary=? WHERE id=?");
            prepareStm.setString(1, customer.getName());
            prepareStm.setString(2, customer.getAddress());
            prepareStm.setDouble(3, customer.getSalary());
            prepareStm.setString(4, customer.getId());
            return prepareStm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        try {
            return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM customer WHERE id='" + customerId + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String customerId) {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT name,address,salary FROM customer WHERE id='" + customerId + "'");
            if (rst.next()) {
                return new Customer(null, rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString("id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

