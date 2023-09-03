package youngjun.readme.domain.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import youngjun.readme.domain.dto.request.RequestUpdatePost;
import youngjun.readme.domain.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    public Post (User writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.voteCount = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime modified;

    private int voteCount;

    public void vote () {
        this.voteCount++;
    }
    public void update (RequestUpdatePost dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
