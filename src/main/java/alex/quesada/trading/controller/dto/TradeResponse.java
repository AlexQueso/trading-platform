package alex.quesada.trading.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
