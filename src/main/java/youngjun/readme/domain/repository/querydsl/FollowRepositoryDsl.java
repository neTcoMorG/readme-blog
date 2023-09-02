package youngjun.readme.domain.repository.querydsl;

import youngjun.readme.domain.entity.user.Follow;
import youngjun.readme.domain.entity.user.User;

import java.util.Optional;

public interface FollowRepositoryDsl {

    boolean isFollowing (User follower, User target);
    Optional<Follow> getFollowByFollowerToFollowing (User follower, User target);
}
