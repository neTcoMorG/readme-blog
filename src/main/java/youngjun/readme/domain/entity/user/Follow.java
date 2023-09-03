package youngjun.readme.domain.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    public Follow (User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "follower_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;

    @JoinColumn(name = "following_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User following;

    @CreatedDate
    private LocalDateTime created;

}
