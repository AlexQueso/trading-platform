package alex.quesada.trading.infrastructure;


import alex.quesada.trading.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
