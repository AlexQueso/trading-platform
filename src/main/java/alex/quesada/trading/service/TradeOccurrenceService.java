package alex.quesada.trading.service;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.OrderType;
import alex.quesada.trading.infrastructure.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeOccurrenceService {

    private final OrderRepository orderRepository;
    private final TradeService tradeService;

    public TradeOccurrenceService(OrderRepository orderRepository, TradeService tradeService) {
        this.orderRepository = orderRepository;
        this.tradeService = tradeService;
    }

    synchronized void checkPossibleTradingOrders(Order order) {
        List<Order> validOppositeTypeOrders = getValidOppositeTypeOrders(order);
        if (!validOppositeTypeOrders.isEmpty()) {
            Order oppositeOrder = validOppositeTypeOrders.getFirst(); // logic to choose best matching order was not provided
            if (order.isBuyOrder()){
                createTrade(order, oppositeOrder);
            } else {
                createTrade(oppositeOrder, order);
            }
        }
    }

    private List<Order> getValidOppositeTypeOrders(Order order){
        return orderRepository.findBySecurityAndTypeAndFulfilled(order.getSecurity(), order.getOppositeOrderType(), false)
                .stream()
                .filter(oppositeTypeOrder -> isCompatibleOrder(order, oppositeTypeOrder))
                .toList();
    }

    private boolean isCompatibleOrder(Order order, Order oppositeTypeOrder) {
        if (order.isBuyOrder()){
            return order.getPrice() >= oppositeTypeOrder.getPrice()
                    && order.getQuantity() <= oppositeTypeOrder.getQuantity();
        } else {
            return order.getPrice() <= oppositeTypeOrder.getPrice()
                    && order.getQuantity() >= oppositeTypeOrder.getQuantity();
        }
    }

    private void createTrade(Order buyOrder, Order sellOrder) {
        tradeService.createTrade(buyOrder, sellOrder);
        buyOrder.setFulfilled(true);
        sellOrder.setFulfilled(true);
        orderRepository.save(buyOrder);
        orderRepository.save(sellOrder);
    }
}
