package alex.quesada.trading.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {
    String id;
    String sellOrderId;
    String buyOrderId;
    double price;
    int quantity;
}
