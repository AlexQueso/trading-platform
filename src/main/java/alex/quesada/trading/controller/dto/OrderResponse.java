package alex.quesada.trading.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    long id;
    long userId;
    long securityId;
    String type;
    double price;
    int quantity;
    boolean fulfilled;
}
