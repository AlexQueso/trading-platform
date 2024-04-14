package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.TradeResponse;
import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.Trade;
import alex.quesada.trading.infrastructure.TradeRepository;
import alex.quesada.trading.service.mapper.TradeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    public TradeService(TradeRepository tradeRepository, TradeMapper tradeMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeMapper = tradeMapper;
    }

    public Optional<TradeResponse> getTradeById(String tradeId) {
        return tradeRepository.findById(tradeId)
                .map(tradeMapper::tradeToTradeResponse);
    }

    public List<TradeResponse> getAll() {
        return tradeRepository.findAll().stream()
                .map(tradeMapper::tradeToTradeResponse)
                .toList();
    }

    public void createTrade(Order buyOrder, Order sellOrder) {
        Trade trade = new Trade(sellOrder, buyOrder, sellOrder.getPrice(), buyOrder.getQuantity());
        tradeRepository.save(trade);
    }
}
