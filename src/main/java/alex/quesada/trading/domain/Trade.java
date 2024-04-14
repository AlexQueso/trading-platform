package alex.quesada.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Order sellOrder;

    @OneToOne
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
