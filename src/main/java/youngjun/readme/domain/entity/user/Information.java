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

    private String linkedin;
    private String github;
    private String website;
}
