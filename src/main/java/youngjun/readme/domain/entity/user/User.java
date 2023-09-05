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

    public User(String email, String tag, String about, String profile_url) {
        this.email = email;
        this.tag = tag;
        this.about = about;
        this.profile_url = profile_url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String tag;
    private String about;
    private String profile_url;

    public void updateTag (String tag) {
        this.tag = tag;
    }

}
