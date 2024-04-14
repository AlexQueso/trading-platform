package alex.quesada.trading.infrastructure;


import alex.quesada.trading.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
