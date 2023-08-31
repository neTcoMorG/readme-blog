package youngjun.readme.domain.service.user;

import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface PostService {

    // Create
    void addPost (User writer, RequestCreatePost requestCreatePost);

    // Read
    Post getPost (Long id);
    List<Post> getPosts (User user);

    // Update
    void updatePost (Post post, RequestUpdatePost requestUpdatePost);

    // Delete
    void removePost (User deleter, Post post);
}
