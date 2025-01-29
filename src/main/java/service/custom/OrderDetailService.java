package service.custom;

import model.OrderDetail;
import service.SuperService;

import java.util.ArrayList;

public interface OrderDetailService extends SuperService {
    boolean addOrderDetail(ArrayList<OrderDetail> orderDetails);
    boolean addOrderDetail(OrderDetail orderDetail);
}
