package youngjun.readme.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUser {

    private long id;
    private String email;
    private String tag;
    private String profile_url;

}
