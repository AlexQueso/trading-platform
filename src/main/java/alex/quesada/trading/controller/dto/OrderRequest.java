package alex.quesada.trading.controller.dto;

import alex.quesada.trading.domain.OrderType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    long userId;
    long securityId;
    OrderType type;
    double price;
    int quantity;
}
