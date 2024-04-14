package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.exception.SecurityNotFoundException;
import alex.quesada.trading.exception.UserNotFoundException;
import alex.quesada.trading.infrastructure.OrderRepository;
import alex.quesada.trading.service.mapper.OrderMapper;
import alex.quesada.trading.utils.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderMapper orderMapper;
    @Mock
    SecurityService securityService;
    @Mock
    UserService userService;
    @Mock
    TradeOccurrenceService tradeOccurrenceService;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void givenNewOrderRequest_whenSavingNewOrderAndUserIsNotFound_thenUserNotFoundExceptionIsThrown() {
        // given
        OrderRequest orderRequest = TestData.getOrderRequest();
        Mockito.when(userService.getUserById(any())).thenThrow(new UserNotFoundException(""));
        // when
        assertThrows(UserNotFoundException.class, () -> orderService.saveNewOrder(orderRequest));
        // then
        Mockito.verify(securityService, Mockito.times(0)).getSecurityById(orderRequest.getSecurityId());
        Mockito.verify(tradeOccurrenceService, Mockito.times(0)).checkPossibleTradingOrders(any());
    }

    @Test
    public void givenNewOrderRequest_whenSavingNewOrderAndSecurityIsNotFound_thenSecurityNotFoundExceptionIsThrown() {
        // given
        OrderRequest orderRequest = TestData.getOrderRequest();
        Mockito.when(userService.getUserById(orderRequest.getUserId()))
                .thenReturn(TestData.getUser());
        Mockito.when(securityService.getSecurityById(orderRequest.getSecurityId()))
                .thenThrow(new SecurityNotFoundException(""));
        // when
        assertThrows(SecurityNotFoundException.class, () -> orderService.saveNewOrder(orderRequest));
        // then
        Mockito.verify(userService).getUserById(orderRequest.getUserId());
        Mockito.verify(tradeOccurrenceService, Mockito.times(0)).checkPossibleTradingOrders(any());
    }

    @Test
    public void givenNewOrderRequest_whenSavingNewOrder_thenTradeOccurrenceIsCheckedAndOrderIsSaved() {
        // given
        OrderRequest orderRequest = TestData.getOrderRequest();
        Mockito.when(userService.getUserById(orderRequest.getUserId()))
                .thenReturn(TestData.getUser());
        Mockito.when(securityService.getSecurityById(orderRequest.getSecurityId()))
                .thenReturn(TestData.getSecurity());
        Mockito.doNothing().when(tradeOccurrenceService).checkPossibleTradingOrders(any());
        Mockito.when(orderRepository.save(any())).thenReturn(TestData.getOrder());
        Mockito.when(orderMapper.orderToOrderResponse(TestData.getOrder())).thenReturn(TestData.getOrderResponse());
        // when
        OrderResponse orderResponse = orderService.saveNewOrder(orderRequest);
        // then
        Assertions.assertThat(orderResponse.getId()).isEqualTo(TestData.getOrder().getId());
        Mockito.verify(userService).getUserById(orderRequest.getUserId());
        Mockito.verify(securityService).getSecurityById(orderRequest.getSecurityId());
        Mockito.verify(tradeOccurrenceService).checkPossibleTradingOrders(any());
        Mockito.verify(orderRepository).save(any());
        Mockito.verify(orderMapper).orderToOrderResponse(TestData.getOrder());
    }

}