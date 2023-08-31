package youngjun.readme.domain.entity.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserFollow {

    public UserFollow (User user, User target) {
        this.user = user;
        this.target = target;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private User target;

}
