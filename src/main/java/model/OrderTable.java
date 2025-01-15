package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTable {
    private String itemCode;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;

    @Override
    public boolean equals(Object o) {
        OrderTable equalsOrder = (OrderTable) o;
        return this.itemCode.equals(equalsOrder.getItemCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, description, qty, unitPrice, total);
    }
}
