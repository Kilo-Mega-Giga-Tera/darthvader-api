package kr.app.darthvader.global.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kr.app.darthvader.domain.user.model.dto.request.TuserResponseDto;
import kr.app.darthvader.global.error.exception.UserMessageException;
import kr.app.darthvader.global.security.constants.SpringSecurityContants;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

public class JWTUtils {

    static final SecretKey KEY = Keys.hmacShaKeyFor(SpringSecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

    public static String tokenGenerator(TuserResponseDto dto, Long min) {
        return Jwts.builder().issuer("Security").subject("JWT Token")
                .claim("username", dto.getUserId())
                .claim("authorities", dto.getRole())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + (min * 60 * 1000)))
                .signWith(KEY)
                .compact();
    }

    public static Claims validator(String token) {
        if (ObjectUtils.isEmpty(token)) {
            throw new UserMessageException("인증정보를 확인하세요");
        }

        token = token.substring(7);

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static Cookie refreshTokenCookieGenerator(String refreshToken) {
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/refresh-token");

        return cookie;
    }

    public static String getUsername() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Claims claims = validator(Objects.requireNonNull(request).getHeader("Authorization"));
        return claims.get("username", String.class);
    }

}
