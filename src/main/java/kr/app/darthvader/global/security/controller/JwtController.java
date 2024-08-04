package kr.app.darthvader.global.security.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.app.darthvader.domain.user.model.dto.request.TuserResponseDto;
import kr.app.darthvader.global.error.exception.UserMessageException;
import kr.app.darthvader.global.security.constants.SpringSecurityContants;
import kr.app.darthvader.global.security.filter.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtController {

    @PostMapping("/refresh-token")
    public Map<String, Object> refreshToken(HttpServletRequest request, HttpServletResponse response,
                                            @RequestHeader("Authorization") String accessToken) {

        if (ObjectUtils.isEmpty(accessToken) || ObjectUtils.isEmpty(request.getCookies())) {
            throw new UserMessageException("다시 로그인 해주세요(토큰 정보 에러1)");
        }

        List<String> cookies = Arrays.stream(request.getCookies())
                .map(m -> m.getName().equals("refresh_token") ? m.getValue() : "")
                .toList();

        if (ObjectUtils.isEmpty(cookies)) {
            throw new UserMessageException("다시 로그인 해주세요(토큰 정보 에러2)");
        }

        String nonBearerRefreshToken = cookies.get(0);
        String refreshToken = SpringSecurityContants.AUTH_TYPE + nonBearerRefreshToken;
        String nonBearerAccessToken = accessToken.substring(7);

        boolean isAccess = checkToken(accessToken);
        boolean isRefresh = checkToken(refreshToken);

        // Access and refresh token both expires
        if (!isAccess && !isRefresh) {
            throw new UserMessageException("다시 로그인 해주세요(로그인 정보 만료)");
        }

        // Access token not expires
        if (isAccess) {
            response.setHeader("Authorization", accessToken);

            // Refresh token need re-create
            if (refreshTokenExprChk(refreshToken)) {
                Claims accessClaims = JWTUtils.validator(accessToken);
                TuserResponseDto dto = new TuserResponseDto((String) accessClaims.get("memberId"), (String) accessClaims.get("authorities"));
                refreshToken = JWTUtils.tokenGenerator(dto, 60L * 24L * 5L);

                Cookie cookie = JWTUtils.refreshTokenCookieGenerator(refreshToken);
                response.addCookie(cookie);
            }

            return Map.of("token", Map.of("access_token", nonBearerAccessToken));
        }

        // Access token expires
        Claims refreshClaims = JWTUtils.validator(refreshToken);
        TuserResponseDto dto = new TuserResponseDto((String) refreshClaims.get("memberId"), (String) refreshClaims.get("authorities"));

        // New access token create
        String newAccessToken = JWTUtils.tokenGenerator(dto, 10L);

        // Refresh token expires check
        String newRefreshToken = refreshTokenExprChk(refreshToken) ? JWTUtils.tokenGenerator(dto, 60L * 24L * 5L) : nonBearerRefreshToken;
        Cookie cookie = JWTUtils.refreshTokenCookieGenerator(newRefreshToken);

        response.setHeader("Authorization", newAccessToken);
        response.addCookie(cookie);

        return Map.of("token", Map.of("access_token", newAccessToken.substring(7)));
    }

    public boolean checkToken(String token) {
        try {
            JWTUtils.validator(token);
            return true;
        } catch (Exception e) {
            if (e.getClass().getSimpleName().equals("ExpiredJwtException")) {
                return false;
            }
            throw new UserMessageException("다시 로그인 해주세요(토큰 정보 에러3)");
        }
    }

    public boolean refreshTokenExprChk(String refreshToken) {
        Claims claims = JWTUtils.validator(refreshToken);
        Long now = new Date().getTime() / 1000;
        Long exp = claims.get("exp", Long.class);

        return ((exp - now) / 60) < 60;
    }

}
