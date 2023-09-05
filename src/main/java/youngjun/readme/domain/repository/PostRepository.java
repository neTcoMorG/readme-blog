package youngjun.readme.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.repository.querydsl.PostRepositoryDsl;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryDsl {
    List<Post> findByWriter (User writer);
}
