package alex.quesada.trading.service;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.infrastructure.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

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

    }

    @ParameterizedTest
    @MethodSource("validOrderList")
    public void givenSeveralValidOppositeOrders_whenCheckPossibleTrading_thenCorrectOrdersShouldBeFilter(
            List<Order> possibleOrders, Order userOrder, Order expectedFilteredOrder) {


    }

    @Test
    public void givenValidOrder_whenCheckPossibleTrading_thenFulfilledStatusShouldChangeAndTradeShouldBeSaved() {

    }

    private static Stream<Arguments> validOrderList() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true)
        );
    }
}