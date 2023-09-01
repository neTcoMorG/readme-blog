package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.user.Follow;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, User> {

    List<User> findByFollower (User user);
    List<User> findByFollowing (User user);

}
