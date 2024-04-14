package alex.quesada.trading.controller;

import alex.quesada.trading.controller.dto.OrderRequest;
import alex.quesada.trading.controller.dto.OrderResponse;
import alex.quesada.trading.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderId") String orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> saveNewOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderService.saveNewOrder(order));
    }

    // PUT, DELETE ...
}
