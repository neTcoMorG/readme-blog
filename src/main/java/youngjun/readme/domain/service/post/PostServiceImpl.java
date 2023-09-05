package youngjun.readme.domain.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.dto.response.ResponseUserPost;
import youngjun.readme.domain.entity.post.Post;
import youngjun.readme.domain.entity.post.Tag;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.exception.MalformedParamException;
import youngjun.readme.domain.exception.NotFoundException;
import youngjun.readme.domain.exception.PermissionDenyException;
import youngjun.readme.domain.repository.PostRepository;
import youngjun.readme.domain.repository.TagRepository;
import youngjun.readme.domain.service.user.UserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public Post addPost (String writerTag, RequestCreatePost requestCreatePost) {
        User writer = userService.getUser(writerTag);
        Post post = postRepository.save(new Post(writer, requestCreatePost.getTitle(), requestCreatePost.getText()));

        for (String t : requestCreatePost.getTags()) {
            tagRepository.save(new Tag(post, t));
        }

        return post;
    }

    @Override
    public Post getPost (Long id) {
        return postRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Post> getPosts (String userTag) {
        User findUser = userService.getUser(userTag);
        return postRepository.findByWriter(findUser);
    }

    @Override
    public Page<ResponseUserPost> getPosts (SearchType type, Pageable pageable) throws MalformedParamException {
        Page<ResponseUserPost> result = null;

        switch (type) {
            case RECENT -> result = postRepository.getPostsRecent(pageable);
            case VIEW -> result   = postRepository.getPostsViewer(pageable);
            case VOTE -> result   = postRepository.getPostsVote(pageable);
        }

        if (result == null) {
            throw new MalformedParamException("검색 조건이 유효하지 않습니다.");
        }

        return result;
    }

    @Override
    @Transactional
    public void updatePost (String editorTag, Long postId, RequestUpdatePost requestUpdatePost) throws PermissionDenyException {
        User editor = userService.getUser(editorTag);
        Post post   = getPost(postId);

        if (!isOwner(post, editor)) {
            throw new PermissionDenyException("본인의 포스트만 수정할 수 있습니다");
        }

        post.update(requestUpdatePost);
    }

    @Override
    @Transactional
    public void removePost (String deleterTag, Long postId) {
        User user = userService.getUser(deleterTag);
        Post post = getPost(postId);

        if (!isOwner(post, user)) {
            throw new PermissionDenyException("본인의 포스트만 삭제할 수 있습니다");
        }

        postRepository.delete(post);
    }

    private boolean isOwner (Post post, User user) {
        return post.getWriter().equals(user);
    }
}
