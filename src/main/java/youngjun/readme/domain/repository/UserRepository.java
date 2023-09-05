package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTag (String tag);
    Optional<User> findByEmail (String email);
}
