package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
