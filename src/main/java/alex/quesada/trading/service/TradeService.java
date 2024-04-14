package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.TradeResponse;
import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.Trade;
import alex.quesada.trading.infrastructure.TradeRepository;
import alex.quesada.trading.service.mapper.TradeMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    public TradeService(TradeRepository tradeRepository, TradeMapper tradeMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeMapper = tradeMapper;
    }

    public Optional<TradeResponse> getTradeById(String tradeId) {
        log.info("Retrieving trade with id {} from database... ", tradeId);
        return tradeRepository.findById(tradeId)
                .map(tradeMapper::tradeToTradeResponse);
    }

    public List<TradeResponse> getAll() {
        log.info("Retrieving all trades from database... ");
        return tradeRepository.findAll().stream()
                .map(tradeMapper::tradeToTradeResponse)
                .toList();
    }

    public void createTrade(Order buyOrder, Order sellOrder) {
        Trade trade = new Trade(sellOrder, buyOrder, sellOrder.getPrice(), buyOrder.getQuantity());
        log.info("Saving new trade for buy order {} and sell order {}. Quantity {} and Price {}",
                buyOrder.getUser().getName(), sellOrder.getUser().getName(), trade.getQuantity(), trade.getPrice());
        tradeRepository.save(trade);
    }
}
