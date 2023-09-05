package youngjun.readme.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ResponseUser {

    @QueryProjection
    public ResponseUser(String email, String tag, String profile_url) {
        this.email = email;
        this.tag = tag;
        this.profile_url = profile_url;
    }

    private String email;
    private String tag;
    private String profile_url;

}
