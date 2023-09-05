package youngjun.readme.domain.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import youngjun.readme.domain.dto.response.ResponseUserPost;

import java.util.List;

public interface PostRepositoryDsl {

    Page<ResponseUserPost> getPostsRecent (Pageable pageable);
    Page<ResponseUserPost> getPostsVote (Pageable pageable);
    Page<ResponseUserPost> getPostsViewer (Pageable pageable);
}
