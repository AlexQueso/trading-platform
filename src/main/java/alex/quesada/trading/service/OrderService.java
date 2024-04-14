package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.domain.User;
import alex.quesada.trading.infrastructure.OrderRepository;
import alex.quesada.trading.infrastructure.SecurityRepository;
import alex.quesada.trading.infrastructure.UserRepository;
import alex.quesada.trading.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final SecurityRepository securityRepository;
    private final UserRepository userRepository;
    private final TradeService tradeService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, SecurityRepository securityRepository,
                        UserRepository userRepository, TradeService tradeService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.securityRepository = securityRepository;
        this.userRepository = userRepository;
        this.tradeService = tradeService;
    }

    public Optional<OrderResponse> getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderResponse);
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderResponse)
                .toList();
    }

    public OrderResponse saveNewOrder(OrderRequest orderRequest) {
        Order order = saveOrder(orderRequest);
        checkPossibleTradingOrders(order);
        return orderMapper.orderToOrderResponse(order);
    }

    private Order saveOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Security security = securityRepository.findById(request.getSecurityId()).orElseThrow();
        Order orderToSave = new Order(user, security, request.getType(), request.getPrice(), request.getQuantity());
        return orderRepository.save(orderToSave);
    }

    synchronized private void checkPossibleTradingOrders(Order order) {
        List<Order> oppositeTypeOrders = orderRepository.findBySecurityAndTypeAndFulfilled(
                order.getSecurity(), order.getOppositeOrderType(), false);
        if (!oppositeTypeOrders.isEmpty()) {
            createTrade(order, getBestMatchingOrder(order, oppositeTypeOrders));
        }
    }

    private Order getBestMatchingOrder(Order order, List<Order> oppositeTypeOrders) {
        // logic to decide the best matching orders can be implemented here
        return oppositeTypeOrders.getFirst();
    }

    private void createTrade(Order order, Order matchingOrder) {
        tradeService.createTrade(order, matchingOrder);
        order.setFulfilled(true);
        matchingOrder.setFulfilled(true);
        orderRepository.save(order);
        orderRepository.save(matchingOrder);
    }

}
