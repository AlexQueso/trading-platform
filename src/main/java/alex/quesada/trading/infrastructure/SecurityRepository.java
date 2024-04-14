package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Security;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecurityRepository extends MongoRepository<Security, String> {
}
