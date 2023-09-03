package youngjun.readme.domain.service.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.dto.response.ResponseUser;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.exception.DuplicateException;
import youngjun.readme.domain.exception.MalformedParamException;
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

    @BeforeEach
    void init () {
        User user1 = new User("test1@test.com", "@test1", "test1_hello", "profile");
        User user2 = new User("test2@test.com", "@test2", "test2_hello", "profile");
        User user3 = new User("test3@test.com", "@test3", "test3_hello", "profile");
        User user4 = new User("test4@test.com", "@test4", "test4_hello", "profile");
        User user5 = new User("test5@test.com", "@test5", "test5_hello", "profile");
        User user6 = new User("test6@test.com", "@test6", "test6_hello", "profile");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

    }

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
        userService.follow("@test1", "@test2");
        List<ResponseUser> followers = userService.getFollowers("@test2");
        assertEquals(followers.size(), 1);
        assertEquals(followers.get(0).getTag(), "@test1");
    }

    @Test
    @DisplayName("@test1이 @test2를 팔로우하고 언팔로우 확인")
    void unfollow () {
        userService.follow("@test1", "@test2");
        userService.follow("@test3", "@test2");
        List<ResponseUser> followers = userService.getFollowers("@test2");
        assertEquals(followers.size() , 2);

        userService.follow("@test1", "@test2");
        followers = userService.getFollowers("@test2");
        assertEquals(followers.size(), 1);

        for (ResponseUser r : followers) {
            System.out.println(r.getTag());
        }

    }

    @Test
    @DisplayName("팔로윙하고 있는 유저 확인")
    void showFollowingUsers () {
        userService.follow("@test1", "@test2");
        userService.follow("@test1", "@test3");
        userService.follow("@test1", "@test4");

        List<ResponseUser> following = userService.getFollowing("@test1");
        assertEquals(following.size(), 3);

        for (ResponseUser r : following) {
            System.out.println(r.getTag());
        }
    }

    @Test
    @DisplayName("자기 자신을 팔로우할 경우 예외 발생")
    void selfFollowExceptionTest () {
        assertThrows(MalformedParamException.class, () -> userService.follow("@test1", "@test1"));
    }
}