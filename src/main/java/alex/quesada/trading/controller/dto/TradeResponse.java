package alex.quesada.trading.controller.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {
    long id;
    long sellOrderId;
    long buyOrderId;
    double price;
    int quantity;
}
