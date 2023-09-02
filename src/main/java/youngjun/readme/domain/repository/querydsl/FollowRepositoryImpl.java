package youngjun.readme.domain.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import youngjun.readme.domain.entity.user.Follow;
import youngjun.readme.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

import static youngjun.readme.domain.entity.user.QFollow.follow;

@AllArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryDsl {

    private final JPAQueryFactory query;

    @Override
    public boolean isFollowing (User src, User dst) {
        List<Follow> fetch = query.selectFrom(follow)
                                .where(follow.follower.eq(src).and(follow.following.eq(dst)))
                                .fetch();

        return !fetch.isEmpty();
    }

    @Override
    public Optional<Follow> getFollowByFollowerToFollowing (User src, User dst) {
        Follow result = query.selectFrom(follow)
                            .where(follow.follower.eq(src).and(follow.following.eq(dst)))
                            .fetchOne();

        return result != null ? Optional.of(result) : Optional.empty();
    }
}
