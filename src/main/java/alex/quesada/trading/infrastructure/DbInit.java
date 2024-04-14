package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Order;
import alex.quesada.trading.domain.OrderType;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final UserRepository userRepository;
    private final SecurityRepository securityRepository;
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public DbInit(UserRepository userRepository, SecurityRepository securityRepository, OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.securityRepository = securityRepository;
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    private void postConstruct(){
        mongoTemplate.getDb().drop();

        Security wsd = new Security("WSD");
        securityRepository.save(wsd);

        User diamond = new User("Diamond", "diamondPassword");
        User paper = new User("Paper", "paperPassword");
        userRepository.save(diamond);
        userRepository.save(paper);

        Order order = new Order(diamond, wsd, OrderType.BUY, 101.0, 50);
        orderRepository.save(order);
    }
}
