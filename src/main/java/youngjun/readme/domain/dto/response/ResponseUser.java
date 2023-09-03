package youngjun.readme.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {

    private long id;
    private String email;
    private String tag;
    private String profile_url;

}
