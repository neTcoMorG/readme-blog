package youngjun.readme.domain.service.user.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import youngjun.readme.domain.service.user.auth.protocol.ResponseAccessToken;
import youngjun.readme.domain.service.user.auth.protocol.ResponseGithubUserObject;
import youngjun.readme.domain.utils.JwtProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final RestTemplate restTemplate;
    private final JwtProvider jwtProvider;

    @Value("${oauth.github.access_token_url}")
    private String accessTokenUrl;

    @Value("${oauth.github.user_url}")
    private String userUrl;

    @Value("${oauth.github.id}")
    private String clientId;

    @Value("${oauth.github.key}")
    private String key;

    @Override
    public String callback (String code) {
        ResponseAccessToken accessToken = getAccessToken(code);
        ResponseGithubUserObject githubObject = getGithubObject(accessToken.getAccess_token());



        return jwtProvider.createToken(1000, githubObject.getEmail());
    }

    private MultiValueMap<String, String> getAccessTokenParam (String cid, String key, String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("client_id", cid);
        map.set("client_secret", key);
        map.set("code", code);
        return map;
    }

    private ResponseAccessToken getAccessToken (String code) throws RestClientException {
        ResponseEntity<ResponseAccessToken> response
                = restTemplate.postForEntity(accessTokenUrl, getAccessTokenParam(clientId, key, code), ResponseAccessToken.class);
        return response.getBody();
    }

    private ResponseGithubUserObject getGithubObject (String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<ResponseGithubUserObject> exchange
                = restTemplate.exchange(userUrl, HttpMethod.GET, requestEntity, ResponseGithubUserObject.class);

        return exchange.getBody();
    }

}

