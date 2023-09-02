package youngjun.readme.domain.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.dto.response.ResponseUser;
import youngjun.readme.domain.entity.user.Follow;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.exception.MalformedParamException;
import youngjun.readme.domain.exception.NotFoundException;
import youngjun.readme.domain.repository.FollowRepository;
import youngjun.readme.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ObjectMapper om;

    @Override
    public User getUser (String tag) {
        return userRepository.findByTag(tag).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    }


    // 함수형 인터페이스를 활용해서 중복 최소화 하기.
    @Override
    public List<ResponseUser> getFollowing (String tag) {
        User user = getUser(tag);
        List<ResponseUser> response = new ArrayList<>();
        followRepository.findByFollower(user).forEach(fw -> response.add(om.convertValue(fw, ResponseUser.class)));
        return response;
    }

    @Override
    public List<ResponseUser> getFollowers (String tag) {
        User user = getUser(tag);
        List<ResponseUser> response = new ArrayList<>();
        followRepository.findByFollowing(user).forEach(fl -> response.add(om.convertValue(fl, ResponseUser.class)));
        return response;
    }

    @Override
    @Transactional
    public void follow (String follower, String following) throws MalformedParamException {
        if (isSelf(follower, following)) {
            throw new MalformedParamException("자기 자신을 팔로우할 수 없습니다");
        }

        User followerUser  = getUser(follower);
        User followingUser = getUser(following);

//        이미 팔로우중이면 언팔로우
        if (followRepository.isFollowing(followerUser, followingUser)) {
            Follow follow = followRepository.getFollowByFollowerToFollowing(followerUser, followingUser).orElseThrow(NotFoundException::new);
            followRepository.delete(follow);
            return;
        }

        followRepository.save(new Follow(followerUser, followingUser));
    }

    private boolean isSelf (String a, String b) {
        return a.equals(b);
    }
}
