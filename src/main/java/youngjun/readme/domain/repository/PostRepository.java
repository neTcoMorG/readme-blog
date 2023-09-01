package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
