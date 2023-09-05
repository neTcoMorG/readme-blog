package youngjun.readme.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import youngjun.readme.domain.exception.UnAuthorizationException;
import youngjun.readme.domain.utils.JwtProvider;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader(HttpHeaders.AUTHORIZATION) == null || request.getHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
            throw new UnAuthorizationException();
        }

        try { jwtProvider.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION)); }
        catch (RuntimeException ex) { return false; }

        return true;
    }
}
