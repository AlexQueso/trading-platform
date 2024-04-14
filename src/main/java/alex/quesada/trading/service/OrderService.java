package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.domain.User;
import alex.quesada.trading.infrastructure.OrderRepository;
import alex.quesada.trading.service.mapper.OrderMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final alex.quesada.trading.service.SecurityService securityService;
    private final UserService userService;
    private final TradeOccurrenceService tradeOccurrenceService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, SecurityService securityService,
                        UserService userService, TradeOccurrenceService tradeOccurrenceService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.securityService = securityService;
        this.userService = userService;
        this.tradeOccurrenceService = tradeOccurrenceService;
    }

    public Optional<OrderResponse> getOrderById(String orderId) {
        log.info("Retrieving order with id {} from database... ", orderId);
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderResponse);
    }

    public List<OrderResponse> getAll() {
        log.info("Retrieving all orders from database... ");
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderResponse)
                .toList();
    }

    public OrderResponse saveNewOrder(OrderRequest orderRequest) {
        Order order = saveOrder(orderRequest);
        tradeOccurrenceService.checkPossibleTradingOrders(order);
        return orderMapper.orderToOrderResponse(order);
    }

    private Order saveOrder(OrderRequest request) {
        User user = userService.getUserById(request.getUserId());
        Security security = securityService.getSecurityById(request.getSecurityId());
        log.info("Saving new order {}... ", request.toString());
        return orderRepository.save(new Order(user, security, request.getType(), request.getPrice(), request.getQuantity()));
    }

}
