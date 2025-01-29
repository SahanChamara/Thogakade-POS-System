package service.custom.impl;

import dbconnection.DBConnection;
import model.OrderDetail;
import service.custom.OrderDetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailServiceImpl implements OrderDetailService {
    private static OrderDetailServiceImpl instance;

    private OrderDetailServiceImpl() {
    }
    public static OrderDetailServiceImpl getInstance(){
        return instance!=null?instance:new OrderDetailServiceImpl();
    }

    @Override
    public boolean addOrderDetail(ArrayList<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            boolean isOrderDetailAdded = addOrderDetail(orderDetail);
            if(!isOrderDetailAdded){
                return false;
            }
        }

        return !orderDetails.isEmpty();
    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        System.out.println(orderDetail);
        try {
            PreparedStatement pst = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");
            pst.setString(1,orderDetail.getOrderId());
            pst.setString(2,orderDetail.getItemCode());
            pst.setInt(3,orderDetail.getQty());
            pst.setDouble(4,orderDetail.getUnitPrice());
            return pst.executeUpdate()>0;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
