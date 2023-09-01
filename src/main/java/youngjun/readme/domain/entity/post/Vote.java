package youngjun.readme.domain.entity.post;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import youngjun.readme.domain.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "post_vote")
@EntityListeners(AuditingEntityListener.class)
public class Vote {

    @Id
    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "voter_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User voter;

    @CreatedDate
    private LocalDateTime voteDate;
}
