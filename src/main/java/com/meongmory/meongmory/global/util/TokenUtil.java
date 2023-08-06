package com.meongmory.meongmory.global.util;


import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import io.jsonwebtoken.*;
import io.netty.handler.codec.compression.CompressionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenUtil {
    public static final String USER_ID = "userId";
    public static final String NICKNAME = "nickname";

    public static final String EMAIL = "email";
    public static final String ONE_BLOCK = " ";
    public static final String COMMA = ",";

    public enum TYPE {
        REFRESH,
        ACCESS
    }

    public static String accessKeyId;
    public static String secretKey;
    public static String tokenType;
    public static String accessName;
    public static String refreshName;
    public static String accessExTime;
    public static String refreshExTime;


    @Value("${jwt.secret}")
    public void accessKeyId(String value) {
        accessKeyId = value;
    }

    @Value("${jwt.secret}")
    public void setSecretKey(String value) {
        secretKey = value;
    }

    @Value("${jwt.token-type}")
    public void setTokenType(String value) {
        tokenType = value;
    }

    @Value("${jwt.access-name}")
    public void setAccessName(String value) {
        accessName = value;
    }

    @Value("${jwt.refresh-name}")
    public void setRefreshName(String value) {
        refreshName = value;
    }

    @Value("${jwt.access-expired-time}")
    public void setAccessExpiredTime(String value) {
        accessExTime = value;
    }

    @Value("${jwt.refresh-expired-time}")
    public void setRefreshExpireTime(String value) {
        refreshExTime = value;
    }

    public String createToken(User user) {
        String access_token = this.createAccessToken(user.getUserId(), user.getNickname());
        String refresh_token = this.createRefreshToken(user.getUserId(), user.getNickname());
        return access_token + COMMA + refresh_token;
    }

    public String createToken(Long id, String email) {
        String access_token = this.createAccessTokenEmail(id, email);
        String refresh_token = this.createRefreshTokenEmail(id, email);
        return access_token + COMMA + refresh_token;
    }

    public String createAccessToken(Long userId, String nickname) {
        Claims claims = Jwts.claims()
                .setSubject(accessName)
                .setIssuedAt(new Date());
        claims.put(USER_ID, userId);
        claims.put(NICKNAME, nickname);
        Date ext = new Date();
        ext.setTime(ext.getTime() + Long.parseLong(Objects.requireNonNull(accessExTime)));
        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return tokenType + ONE_BLOCK + accessToken;
    }

    public String createRefreshToken(Long userId, String nickname) {
        Claims claims = Jwts.claims()
                .setSubject(refreshName)
                .setIssuedAt(new Date());
        claims.put(USER_ID, userId);
        claims.put(NICKNAME, nickname);
        Date ext = new Date();
        ext.setTime(ext.getTime() + Long.parseLong(Objects.requireNonNull(refreshExTime)));
        String refreshToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
//        redisTemplateService.setUserRefreshToken(userIdx.toString(), tokenType + ONE_BLOCK + refreshToken);
        return tokenType + ONE_BLOCK + refreshToken;
    }

    public String createAccessTokenEmail(Long userId, String email) {
        Claims claims = Jwts.claims()
                .setSubject(accessName)
                .setIssuedAt(new Date());
        claims.put(USER_ID, userId);
        claims.put(EMAIL, email);
        Date ext = new Date();
        ext.setTime(ext.getTime() + Long.parseLong(Objects.requireNonNull(accessExTime)));
        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return tokenType + ONE_BLOCK + accessToken;
    }

    public String createRefreshTokenEmail(Long userId, String email) {
        Claims claims = Jwts.claims()
                .setSubject(refreshName)
                .setIssuedAt(new Date());
        claims.put(USER_ID, userId);
        claims.put(EMAIL, email);
        Date ext = new Date();
        ext.setTime(ext.getTime() + Long.parseLong(Objects.requireNonNull(refreshExTime)));
        String refreshToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
//        redisTemplateService.setUserRefreshToken(userIdx.toString(), tokenType + ONE_BLOCK + refreshToken);
        return tokenType + ONE_BLOCK + refreshToken;
    }

    public boolean isValidToken(String justToken) {
        if (justToken != null && justToken.split(ONE_BLOCK).length == 2)
            justToken = justToken.split(ONE_BLOCK)[1];
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(justToken).getBody();
            return true;
        } catch (ExpiredJwtException exception) {
            log.error("Token Tampered");
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Token MalformedJwtException");
            return false;
        } catch (ClaimJwtException exception) {
            log.error("Token ClaimJwtException");
            return false;
        } catch (UnsupportedJwtException exception) {
            log.error("Token UnsupportedJwtException");
            return false;
        } catch (CompressionException exception) {
            log.error("Token CompressionException");
            return false;
        } catch (RequiredTypeException exception) {
            log.error("Token RequiredTypeException");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        } catch (Exception exception) {
            log.error("Undefined ERROR");
            return false;
        }
    }

    private Claims getJwtBodyFromJustToken(String justToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(justToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BaseException(BaseResponseCode.TOKEN_EXPIRATION);
        }
    }

    public boolean isTokenExpired(String justToken) {
        if (justToken != null && justToken.split(ONE_BLOCK).length == 2)
            justToken = justToken.split(ONE_BLOCK)[1];
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(justToken).getBody();
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getUserIdFromFullToken(String fullToken) {
        return String.valueOf(getJwtBodyFromJustToken(parseJustTokenFromFullToken(fullToken)).get(USER_ID));
    }

    public String getNicknameFromFullToken(String fullToken) {
        return String.valueOf(getJwtBodyFromJustToken(parseJustTokenFromFullToken(fullToken)).get(NICKNAME));
    }

    // "Bearer eyi35..." 로 부터 "Bearer " 이하만 떼어내는 메서드
    public String parseJustTokenFromFullToken(String fullToken) {
        if (StringUtils.hasText(fullToken)
                &&
                fullToken.startsWith(Objects.requireNonNull(tokenType))
        )
            return fullToken.split(ONE_BLOCK)[1]; // e부터 시작하는 jwt 토큰
        return null;
    }

//    @Transactional
//    public String accessExpiration(Long userIdx) {
//        String userRefreshToken = redisTemplateService.getUserRefreshToken(userIdx.toString());
//        if (userRefreshToken == null) throw new TokenExpirationException();
//        String refreshNickname = getNicknameFromFullToken(userRefreshToken);
//        if (refreshNickname.isEmpty()) throw new TokenExpirationException();
//
//        //토큰이 만료되었을 경우.
//        return createAccessToken(userIdx, refreshNickname);
//    }

}