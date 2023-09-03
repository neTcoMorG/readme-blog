package youngjun.readme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.readme.domain.entity.post.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
