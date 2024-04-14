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
public class Order {

    @Id
    private String id;
    @DocumentReference
    private User user;
    @DocumentReference
    private Security security;
    private OrderType type;
    private Double price;
    private Integer quantity;
    private Boolean fulfilled = false;

    public Order(User user, Security security, OrderType type, Double price, Integer quantity) {
        this.user = user;
        this.security = security;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.fulfilled = false;
    }

    public OrderType getOppositeOrderType() {
        if (this.type.equals(OrderType.BUY)) {
            return OrderType.SELL;
        } else {
            return OrderType.BUY;
        }
    }

    public boolean isBuyOrder() {
        return this.type.equals(OrderType.BUY);
    }
}
