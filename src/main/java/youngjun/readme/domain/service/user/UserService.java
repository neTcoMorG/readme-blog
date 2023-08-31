package youngjun.readme.domain.service.user;

import youngjun.readme.domain.entity.user.User;

import java.util.List;

public interface UserService {

    User getUser (String tag);
    List<User> getFollows (String tag);
    List<User> getFollowers (String tag);
}
