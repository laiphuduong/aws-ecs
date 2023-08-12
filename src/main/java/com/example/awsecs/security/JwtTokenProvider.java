package com.example.awsecs.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.example.awsecs.config.JwtConfigurationProperties;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtConfigurationProperties jwtConfigurationProperties;
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;
    private final long JWT_REFRESH_EXPIRATION = 1209600000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        Map<String, Object> claim = new HashMap<>();
        claim.put("test", "duong");
        claim.put("test1", "duong");
        claim.put("test2", "duong");
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .addClaims(claim)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfigurationProperties.getSecret())
                .compact();
    }

    // Lấy thông tin user từ jwt
    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfigurationProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            var value = Jwts.parser().setSigningKey(jwtConfigurationProperties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String generateRefreshToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_REFRESH_EXPIRATION);
        Map<String, Object> claim = new HashMap<>();
        claim.put("test", "duong");
        claim.put("test1", "duong");
        claim.put("test2", "duong");
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .addClaims(claim)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfigurationProperties.getRefreshSecret())
                .compact();
    }

    public boolean validateRefreshToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfigurationProperties.getRefreshSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Integer getUserIdFromJWTRefresh(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfigurationProperties.getRefreshSecret())
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }
}
