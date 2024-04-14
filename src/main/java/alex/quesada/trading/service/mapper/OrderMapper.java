package alex.quesada.trading.service.mapper;

import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "order.user.id")
    @Mapping(target = "securityId", source = "order.security.id")
    OrderResponse orderToOrderResponse(Order order);
}
