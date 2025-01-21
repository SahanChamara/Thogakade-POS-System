package controller.orderdetail;

import model.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailService {
    boolean addOrderDetail(ArrayList<OrderDetail> orderDetails);
    boolean addOrderDetail(OrderDetail orderDetail);
}
