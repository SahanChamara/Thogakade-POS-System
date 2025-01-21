package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders {
    private String id;
    private String date;
    private String customerId;
    private ArrayList<OrderDetail> orderDetailList;
}
