package alex.quesada.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Security security;

    @Enumerated(EnumType.STRING)
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
}
