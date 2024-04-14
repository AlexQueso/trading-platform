package alex.quesada.trading.utils;

import alex.quesada.trading.domain.Order;

import static alex.quesada.trading.domain.OrderType.BUY;
import static alex.quesada.trading.domain.OrderType.SELL;
import static alex.quesada.trading.utils.TestData.*;

public class OrderTestData {

    public static Order getSellOrder() {
        return Order.builder()
                .id("1")
                .user(getUser())
                .security(getSecurity())
                .type(SELL)
                .price(PRICE)
                .quantity(QUANTITY)
                .fulfilled(false)
                .build();
    }

    public static Order getBuyOrder() {
        return Order.builder()
                .id("2")
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(PRICE)
                .quantity(QUANTITY)
                .fulfilled(false)
                .build();
    }

    public static Order getSellOrderLowPriceHighQuantity() {
        return Order.builder()
                .id("3")
                .user(getUser())
                .security(getSecurity())
                .type(SELL)
                .price(20.0)
                .quantity(200)
                .fulfilled(false)
                .build();
    }

    public static Order getSellOrderHighPriceHighQuantity() {
        return Order.builder()
                .id("4")
                .user(getUser())
                .security(getSecurity())
                .type(SELL)
                .price(150.0)
                .quantity(200)
                .fulfilled(false)
                .build();
    }

    public static Order getSellOrderHighPriceLowQuantity() {
        return Order.builder()
                .id("5")
                .user(getUser())
                .security(getSecurity())
                .type(SELL)
                .price(150.0)
                .quantity(15)
                .fulfilled(false)
                .build();
    }

    public static Order getSellOrderLowPriceLowQuantity() {
        return Order.builder()
                .id("6")
                .user(getUser())
                .security(getSecurity())
                .type(SELL)
                .price(20.0)
                .quantity(15)
                .fulfilled(false)
                .build();
    }

    public static Order getBuyOrderLowPriceHighQuantity() {
        return Order.builder()
                .id("7")
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(20.0)
                .quantity(200)
                .fulfilled(false)
                .build();
    }

    public static Order getBuyOrderHighPriceHighQuantity() {
        return Order.builder()
                .id("8")
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(150.0)
                .quantity(200)
                .fulfilled(false)
                .build();
    }

    public static Order getBuyOrderHighPriceLowQuantity() {
        return Order.builder()
                .id("9")
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(150.0)
                .quantity(15)
                .fulfilled(false)
                .build();
    }

    public static Order getBuyOrderLowPriceLowQuantity() {
        return Order.builder()
                .id("10")
                .user(getUser())
                .security(getSecurity())
                .type(BUY)
                .price(20.0)
                .quantity(15)
                .fulfilled(false)
                .build();
    }


}
