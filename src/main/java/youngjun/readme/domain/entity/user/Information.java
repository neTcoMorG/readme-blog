package youngjun.readme.domain.entity.user;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Information {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "linkedIn")
    private String linkedIn;
    private String github;
    private String website;
}
