package youngjun.readme.domain.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import youngjun.readme.domain.dto.response.ResponseUser;
import youngjun.readme.domain.entity.user.User;
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
}
