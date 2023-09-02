package youngjun.readme.domain.service.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.dto.response.ResponseUser;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("나를 팔로우 하고 있는 유저들 확인")
    void getFollows() {
        User user = userRepository.findByTag("@youngjun").orElseThrow();
        List<ResponseUser> followers = userService.getFollowers(user.getTag());
        assertEquals(followers.size(), 0);
    }

    @Test
    @DisplayName("@youngjun이 @test1를 팔로우하고 확인")
    void getFollowers() {
        userService.follow("@youngjun", "@test1");
        List<ResponseUser> followers = userService.getFollowers("@test1");
        assertEquals(followers.size(), 1);

        for (ResponseUser r : followers) {
            System.out.println(r.getTag());
        }
    }
}