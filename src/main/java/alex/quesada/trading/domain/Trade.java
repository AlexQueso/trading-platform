package alex.quesada.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Trade {

    @Id
    private String id;
    @DocumentReference
    private Order sellOrder;
    @DocumentReference
    private Order buyOrder;
    private Double price;
    private Integer quantity;

    public Trade(Order sellOrder, Order buyOrder, Double price, Integer quantity) {
        this.sellOrder = sellOrder;
        this.buyOrder = buyOrder;
        this.price = price;
        this.quantity = quantity;
    }
}
