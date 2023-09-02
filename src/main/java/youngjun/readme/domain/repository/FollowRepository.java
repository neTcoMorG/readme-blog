package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.user.Follow;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.repository.querydsl.FollowRepositoryDsl;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, User>, FollowRepositoryDsl {

    List<Follow> findByFollower (User user);
    List<Follow> findByFollowing (User user);

}
