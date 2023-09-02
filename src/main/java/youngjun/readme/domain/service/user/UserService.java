package youngjun.readme.domain.service.user;

import youngjun.readme.domain.dto.response.ResponseUser;
import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface UserService {

    User getUser (String tag);
    List<ResponseUser> getFollowing (String tag);
    List<ResponseUser> getFollowers (String tag);
    void follow (String follower, String following);
}
