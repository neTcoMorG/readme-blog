package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.post.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
