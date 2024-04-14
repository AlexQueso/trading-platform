package alex.quesada.trading.infrastructure;

import alex.quesada.trading.domain.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, Long> {
}
