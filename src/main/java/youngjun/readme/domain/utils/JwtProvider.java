package youngjun.readme.domain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken (int expiration, String email) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, expiration);

        Claims claims = Jwts.claims()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(calendar.getTimeInMillis()));
        claims.put("email", email);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String validateToken (String token) throws RuntimeException {
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
