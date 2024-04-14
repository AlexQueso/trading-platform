package alex.quesada.trading.controller;

import alex.quesada.trading.controller.dto.TradeResponse;
import alex.quesada.trading.service.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trades/")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("{tradeId}")
    public ResponseEntity<TradeResponse> getTradeById(@PathVariable("tradeId") String tradeId) {
        return tradeService.getTradeById(tradeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TradeResponse> getAll() {
        return tradeService.getAll();
    }

    // PUT, DELETE ...
}
