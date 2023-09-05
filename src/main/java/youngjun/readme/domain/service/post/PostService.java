package youngjun.readme.domain.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.dto.response.ResponseUserPost;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface PostService {

    // Create
    Post addPost (String writerTag, RequestCreatePost requestCreatePost);

    // Read
    Post getPost (Long id);
    List<Post> getPosts (String userTag);
    Page<ResponseUserPost> getPosts (SearchType type, Pageable pageable);

    // Update
    void updatePost (String editorTag, Long postId, RequestUpdatePost requestUpdatePost);

    // Delete
    void removePost (String deleterTag, Long postId);
}
