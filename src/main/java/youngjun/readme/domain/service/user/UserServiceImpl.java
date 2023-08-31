package youngjun.readme.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.exception.NotFoundException;
import youngjun.readme.domain.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String tag) {
        return userRepository.findByTag(tag).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    }

    @Override
    public List<User> getFollows (String tag) {
        return null;
    }

    @Override
    public List<User> getFollowers (String tag) {
        return null;
    }
}
