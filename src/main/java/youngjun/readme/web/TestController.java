package youngjun.readme.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youngjun.readme.domain.dto.request.RequestCreatePost;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.repository.UserRepository;
import youngjun.readme.domain.service.post.PostService;
import youngjun.readme.domain.service.post.SearchType;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PostService postService;

    private final UserRepository userRepository;

    @GetMapping("/")
    public HttpEntity<?> test (Pageable pageable) {
        return ResponseEntity.ok(postService.getPosts(SearchType.RECENT, pageable));
    }

    @GetMapping("/create/{id}")
    public HttpEntity<?> createUserTest (@PathVariable String id) {
        userRepository.save(new User(id, "@"+id, "about", "profile"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post/{id}")
    public HttpEntity<?> addPost (@PathVariable String id, @RequestBody RequestCreatePost post) {
        return ResponseEntity.ok(postService.addPost(id, post));
    }
}
