package youngjun.readme.domain.service.user.auth.protocol;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseGithubUserObject {
    private String email;
    private String url;
    private String avatar_url;
}
