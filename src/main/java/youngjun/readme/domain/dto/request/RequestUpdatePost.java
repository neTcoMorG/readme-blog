package youngjun.readme.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUpdatePost {

    private String title;
    private String content;
}
