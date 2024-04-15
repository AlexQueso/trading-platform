package alex.quesada.trading.service;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.infrastructure.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TradeOccurrenceService {

    private final OrderRepository orderRepository;
    private final TradeService tradeService;

    public TradeOccurrenceService(OrderRepository orderRepository, TradeService tradeService) {
        this.orderRepository = orderRepository;
        this.tradeService = tradeService;
    }

    synchronized void checkPossibleTradingOrders(Order order) {
        log.info("Checking if new trade has to occur after order with id: {} was created", order.getId());
        List<Order> validOppositeTypeOrders = getValidOppositeTypeOrders(order);
        if (!validOppositeTypeOrders.isEmpty()) {
            Order oppositeOrder = validOppositeTypeOrders.getFirst(); // logic to choose best matching order was not provided
            log.info("Valid opposite order was found for order wit id: {}", order.getId());
            if (order.isBuyOrder()) {
                createTrade(order, oppositeOrder);
            } else {
                createTrade(oppositeOrder, order);
            }
        }
    }

    private List<Order> getValidOppositeTypeOrders(Order order) {
        log.info("Getting possible opposite orders from database...");
        return orderRepository.findBySecurityAndTypeAndFulfilled(order.getSecurity(), order.getOppositeOrderType(), false)
                .stream()
                .filter(oppositeTypeOrder -> isCompatibleOrder(order, oppositeTypeOrder))
                .toList();
    }

    private boolean isCompatibleOrder(Order order, Order oppositeTypeOrder) {
        if (order.isBuyOrder()) {
            return order.getPrice() >= oppositeTypeOrder.getPrice()
                    && order.getQuantity() <= oppositeTypeOrder.getQuantity();
        } else {
            return order.getPrice() <= oppositeTypeOrder.getPrice()
                    && order.getQuantity() >= oppositeTypeOrder.getQuantity();
        }
    }

    private void createTrade(Order buyOrder, Order sellOrder) {
        tradeService.createTrade(buyOrder, sellOrder);
        log.info("Changing fulfilled status for orders with ids {} and {}", buyOrder.getId(), sellOrder.getId());
        buyOrder.setFulfilled(true);
        sellOrder.setFulfilled(true);
        orderRepository.save(buyOrder);
        orderRepository.save(sellOrder);
    }
}
