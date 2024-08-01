package kr.app.darthvader.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.darthvader.domain.common.model.dto.ErrorResult;
import kr.app.darthvader.global.error.exception.UserMessageException;
import kr.app.darthvader.global.security.constants.SpringSecurityContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class JWTValidatorFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(SpringSecurityContants.JWT_HEADER);

        if (ObjectUtils.isEmpty(jwt)) {
            exceptionHandler(response, "인증 정보가 입력되지 않았습니다");
        }

        try {
            String[] token = jwt.split(" ");

            if (!SpringSecurityContants.AUTH_TYPE.trim().equals(token[0])) {
                exceptionHandler(response, "유효하지 않은 인증 타입입니다");
            }

            SecretKey key = Keys.hmacShaKeyFor(SpringSecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token[1])
                    .getPayload();

            String username = claims.get("username", String.class);
            String authorities = claims.get("authorities", String.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            exceptionHandler(response, "유효하지 않은 인증 정보입니다");
        }

        filterChain.doFilter(request, response);
    }

    private void exceptionHandler(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");
        objectMapper.writeValue(response.getWriter(), new ErrorResult<>(Map.of(
                "message", message,
                "status", String.valueOf(HttpStatus.UNAUTHORIZED.value()))));
        throw new UserMessageException(message);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("/login");
    }

}
