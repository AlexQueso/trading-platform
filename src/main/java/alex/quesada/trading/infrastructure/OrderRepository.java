package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.OrderType;
import alex.quesada.trading.domain.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findBySecurityAndTypeAndFulfilled(Security security, OrderType type, Boolean fulfilled);
}
