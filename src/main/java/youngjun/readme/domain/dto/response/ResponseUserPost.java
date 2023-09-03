package youngjun.readme.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseUserPost {

    private ResponseUser writer;
    private String title;
    private String text;
    private LocalDateTime postDate;

}
