package youngjun.readme.domain.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Information {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String linkedin;
    private String github;
    private String website;
}
