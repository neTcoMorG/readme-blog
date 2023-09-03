package youngjun.readme.domain.service.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.exception.PermissionDenyException;
import youngjun.readme.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void init () {
        User user1 = new User("test1@test.com", "@test1", "test1_hello", "profile");
        userRepository.save(user1);
    }

    @Test
    @DisplayName("포스트 생성 테스트")
    void addPost () {
        List<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("spring");
        RequestCreatePost post = new RequestCreatePost("테스트 1", "테스트 1의 본문",  tags);

        Post createdPost = postService.addPost("@test1", post);
        List<Post> posts = postService.getPosts("@test1");

        assertEquals(posts.size(), 1);
        assertEquals(createdPost.getWriter().getTag(), "@test1");
        assertEquals(createdPost.getTitle(), "테스트 1");
    }

    @Test
    @DisplayName("포스트 수정 테스트")
    void updatePost () {
        Post beforePost = createPost("@test1");
        RequestUpdatePost update = new RequestUpdatePost("테스트 1 수정", beforePost.getContent());
        postService.updatePost("@test1", beforePost.getId(), update);

        entityManager.flush();
        entityManager.clear();

        Post afterPost = postService.getPost(beforePost.getId());
        assertEquals(afterPost.getTitle(), "테스트 1 수정");
    }

    @Test
    @DisplayName("다른 사람 포스트 수정시 예외 발생")
    void updatePermissionDeny () {
        User test2 = userRepository.save(new User("test2@test.com", "@test2", "sad", "asd"));
        Post post = createPost(test2.getTag());

        assertThrows(PermissionDenyException.class,
                () -> postService.updatePost("@test1", post.getId(), new RequestUpdatePost("asd", "sdsd")));

    }

    @Test
    @DisplayName("포스트 삭제 테스트")
    void deletePost () {
        Post post = createPost("@test1");
        postService.removePost("@test1", post.getId());

        entityManager.flush();
        entityManager.clear();

        assertThrows(Exception.class, () -> postService.getPost(post.getId()));
    }

    @Test
    @DisplayName("다른 사람이 포스트 삭제시 예외 발생")
    void deletePermissionDeny () {
        User test2 = userRepository.save(new User("test2@test.com", "@test2", "sad", "asd"));
        Post post = createPost(test2.getTag());
        assertThrows(PermissionDenyException.class, () -> postService.removePost("@test1", post.getId()));
    }

    private Post createPost(String writerTag) {
        List<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("spring");
        RequestCreatePost post = new RequestCreatePost("테스트 1", "테스트 1의 본문",  tags);
        return postService.addPost(writerTag, post);
    }
}