package youngjun.readme.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class ResponseUserPost {

    @QueryProjection
    public ResponseUserPost(ResponseUser writer, String title, String text, LocalDateTime postDate) {
        this.writer = writer;
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }

    private ResponseUser writer;
    private String title;
    private String text;
    private LocalDateTime postDate;

}
