package alex.quesada.trading.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    String id;
    String userId;
    String securityId;
    String type;
    double price;
    int quantity;
    boolean fulfilled;
}
