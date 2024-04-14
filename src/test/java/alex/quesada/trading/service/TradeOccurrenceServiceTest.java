package alex.quesada.trading.service;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.infrastructure.OrderRepository;
import alex.quesada.trading.utils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static alex.quesada.trading.utils.OrderTestData.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TradeOccurrenceServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    TradeService tradeService;

    @InjectMocks
    TradeOccurrenceService tradeOccurrenceService;


    @Test
    public void givenEmptyValidOppositeOrders_whenCheckPossibleTrading_thenDoNothing() {
        // given
        Order order = TestData.getOrder();
        Mockito.when(orderRepository.findBySecurityAndTypeAndFulfilled(any(), any(), any())).thenReturn(List.of());
        // when
        tradeOccurrenceService.checkPossibleTradingOrders(order);
        // then
        Mockito.verify(orderRepository).findBySecurityAndTypeAndFulfilled(any(), any(), any());
        Mockito.verify(tradeService, Mockito.times(0)).createTrade(any(), any());
    }

    @Test
    public void givenSeveralSellValidOppositeOrders_whenCheckPossibleTrading_thenCorrectOrderShouldBeFilter() {
        // given
        List<Order> possibleSellOrders = getSellOrderList();
        Order buyOrder = getBuyOrder();
        Mockito.when(orderRepository.findBySecurityAndTypeAndFulfilled(any(), any(), any())).thenReturn(possibleSellOrders);
        Order expectedSellOrder = getSellOrderLowPriceHighQuantity();
        expectedSellOrder.setFulfilled(true);
        // when
        tradeOccurrenceService.checkPossibleTradingOrders(buyOrder);
        // then
        Mockito.verify(orderRepository).findBySecurityAndTypeAndFulfilled(any(), any(), any());
        Mockito.verify(tradeService).createTrade(buyOrder, expectedSellOrder);
    }

    @Test
    public void givenSeveralBuyValidOppositeOrders_whenCheckPossibleTrading_thenCorrectOrderShouldBeFilter() {
        // given
        List<Order> possibleBuyOrders = getBuyOrderList();
        Order sellOrder = getSellOrder();
        Mockito.when(orderRepository.findBySecurityAndTypeAndFulfilled(any(), any(), any())).thenReturn(possibleBuyOrders);
        Order expectedBuyOrder = getBuyOrderHighPriceLowQuantity();
        expectedBuyOrder.setFulfilled(true);
        // when
        tradeOccurrenceService.checkPossibleTradingOrders(sellOrder);
        // then
        Mockito.verify(orderRepository).findBySecurityAndTypeAndFulfilled(any(), any(), any());
        Mockito.verify(tradeService).createTrade(expectedBuyOrder, sellOrder);
    }

    private List<Order> getSellOrderList() {
        return List.of(
                getSellOrderLowPriceLowQuantity(),
                getSellOrderLowPriceHighQuantity(),
                getSellOrderHighPriceLowQuantity(),
                getSellOrderHighPriceHighQuantity()
        );
    }

    private List<Order> getBuyOrderList() {
        return List.of(
                getBuyOrderLowPriceLowQuantity(),
                getBuyOrderLowPriceHighQuantity(),
                getBuyOrderHighPriceLowQuantity(),
                getBuyOrderHighPriceHighQuantity()
        );
    }
}