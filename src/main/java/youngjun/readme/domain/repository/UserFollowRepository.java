package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.entity.user.UserFollow;

public interface UserFollowRepository extends JpaRepository<UserFollow, User> {

}
