package alex.quesada.trading.controller.dto;

import alex.quesada.trading.domain.OrderType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
    String userId;
    String securityId;
    OrderType type;
    double price;
    int quantity;
}
