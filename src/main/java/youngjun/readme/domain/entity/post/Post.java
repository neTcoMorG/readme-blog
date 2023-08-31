package youngjun.readme.domain.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import youngjun.readme.domain.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    public Post (User user, String title, String preview) {
        this.user = user;
        this.title = title;
        this.preview = preview;
        this.goodCount = 0;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private LocalDateTime created;

    private String title;
    private String preview;

    private long goodCount;

    public void increaseGoodCount () {
        this.goodCount++;
    }
}
