package alex.quesada.trading.controller.dto;

import lombok.*;

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
