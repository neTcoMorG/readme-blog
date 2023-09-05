package youngjun.readme.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import youngjun.readme.domain.service.user.auth.UserAuthService;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final UserAuthService userAuthService;

    @GetMapping("/github")
    public HttpEntity<?> github (@RequestParam String code) throws Exception {
        return ResponseEntity.ok(userAuthService.callback(code));
    }
}
