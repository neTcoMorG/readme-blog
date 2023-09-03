package youngjun.readme.domain.service.post;

import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface PostService {

    // Create
    Post addPost (String writerTag, RequestCreatePost requestCreatePost);

    // Read
    Post getPost (Long id);
    List<Post> getPosts (String userTag);

    // Update
    void updatePost (String editorTag, Long postId, RequestUpdatePost requestUpdatePost);

    // Delete
    void removePost (String deleterTag, Long postId);
}
