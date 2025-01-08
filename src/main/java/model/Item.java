package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Item {
    private String code;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}
