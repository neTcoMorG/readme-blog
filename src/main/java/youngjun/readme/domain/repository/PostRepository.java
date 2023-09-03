package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByWriter (User writer);
}
