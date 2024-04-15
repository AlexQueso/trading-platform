package alex.quesada.trading.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeResponse {
    String id;
    String sellOrderId;
    String buyOrderId;
    double price;
    int quantity;
}
