package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.OrderType;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final UserRepository userRepository;
    private final SecurityRepository securityRepository;
    private final OrderRepository orderRepository;

    public DbInit(UserRepository userRepository, SecurityRepository securityRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.securityRepository = securityRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    private void postConstruct(){
        Security security = new Security("WSD");
        User diamond = new User("diamond", "diamondPassword");
        User paper = new User("paper", "paperPassword");
        securityRepository.save(security);
        userRepository.save(diamond);
        userRepository.save(paper);
        Order order = new Order(paper, security, OrderType.BUY, 100.0, 50);
        orderRepository.save(order);
    }
}
