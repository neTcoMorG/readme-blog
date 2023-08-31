package youngjun.readme.domain.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Follow {

    @Id
    @JoinColumn(name = "follower_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;

    @JoinColumn(name = "following_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User following;

    @CreatedDate
    private LocalDateTime created;

}
