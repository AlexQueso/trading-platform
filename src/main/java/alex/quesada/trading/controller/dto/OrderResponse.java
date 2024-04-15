package alex.quesada.trading.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    String id;
    String userId;
    String securityId;
    String type;
    double price;
    int quantity;
    boolean fulfilled;
}
