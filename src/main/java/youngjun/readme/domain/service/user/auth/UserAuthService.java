package youngjun.readme.domain.service.user.auth;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserAuthService {

    String callback (String code) throws JsonProcessingException;

}
