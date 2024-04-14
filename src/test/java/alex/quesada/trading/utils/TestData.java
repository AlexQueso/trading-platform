package alex.quesada.trading.utils;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.domain.User;

import static alex.quesada.trading.domain.OrderType.BUY;

public class TestData {

    public static final String USER_ID = "userId";
    public static final String SECURITY_ID = "securityId";
    public static final String ORDER_ID = "orderId";
    public static final String TRADE_ID = "tradeId";
    public static final double PRICE = 100.0;
    public static final int QUANTITY = 50;


    public static OrderRequest getOrderRequest() {
        return OrderRequest.builder()
                .userId(USER_ID)
                .securityId(SECURITY_ID)
                .type(BUY)
                .price(PRICE)
                .quantity(QUANTITY)
                .build();
    }

    public static OrderResponse getOrderResponse() {
        return OrderResponse.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .securityId(SECURITY_ID)
                .type("BUY")
                .price(PRICE)
                .quantity(QUANTITY)
                .fulfilled(true)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .id(USER_ID)
                .name("userName")
                .password("userPassword")
                .build();
    }

    public static Security getSecurity() {
        return Security.builder()
                .id(SECURITY_ID)
                .name("securityName")
                .build();
    }

    public static Order getOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(PRICE)
                .quantity(QUANTITY)
                .fulfilled(false)
                .build();
    }


}
