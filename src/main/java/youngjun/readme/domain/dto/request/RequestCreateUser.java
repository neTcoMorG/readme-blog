package youngjun.readme.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateUser {

    private String email;
    private String tag;
    private String password;
    private String profile_image;
    private String about;

}
