package kr.app.darthvader.global.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.app.darthvader.global.security.constants.SpringSecurityContants;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTGenerator {

    public static String Gen(String userId, String role) {
        SecretKey key = Keys.hmacShaKeyFor(SpringSecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().issuer("Security").subject("JWT Token")
                .claim("username", userId)
                .claim("authorities", role)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key)
                .compact();
    }

}
