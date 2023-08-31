package youngjun.readme.domain.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    public User (String email, String tag, String password, String about, String profileImage) {
        this.email = email;
        this.tag = tag;
        this.password = password;
        this.about = about;
        this.profileImage = profileImage;
    }

    public User (String email, String tag, String password) {
        this(email, tag, password, null, null);
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String tag;
    private String password;

    private String about;
    private String profileImage;
}
