package youngjun.readme.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RequestCreatePost {

    private String title;
    private String text;
    private List<String> tags;

}
