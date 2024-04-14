package alex.quesada.trading.service.mapper;

import alex.quesada.trading.controller.dto.TradeResponse;
import alex.quesada.trading.domain.Trade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TradeMapper {

    @Mapping(target = "sellOrderId", source = "trade.sellOrder.id")
    @Mapping(target = "buyOrderId", source = "trade.buyOrder.id")
    TradeResponse tradeToTradeResponse (Trade trade);
}
