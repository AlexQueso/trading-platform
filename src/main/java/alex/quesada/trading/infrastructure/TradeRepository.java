package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade, String> {
}
