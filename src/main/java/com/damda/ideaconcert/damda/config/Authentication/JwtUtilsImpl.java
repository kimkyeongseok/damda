package com.damda.ideaconcert.damda.config.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.damda.ideaconcert.damda.user.application.JwtUtils;
import com.damda.ideaconcert.damda.user.application.Payload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtilsImpl implements JwtUtils {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Value("${jwt.sign.key}")
    private String SIGN_KEY;
    private String ISSUER = "damda";

    @Override
    public String createToken(String userPk, String nickName, String userId, String role) {
        Date EXPIRED_TIME = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 7 * 1000L);
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return Jwts.builder()
            .setHeaderParams(header)
            .setIssuer(ISSUER) //토큰 발행자
            .claim("nickName", nickName)
            .claim("userId", userId)
            .claim("role", role)
            .setAudience(userPk) // 토큰 사용 대상
            .setExpiration(EXPIRED_TIME)// 만료 기한
            .signWith(signatureAlgorithm, SIGN_KEY)
            .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        if(!StringUtils.isEmpty(token)){
            try {
                Jwts.parser().setSigningKey(SIGN_KEY).parse(token);
                return true;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return false;
    }

    @Override
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SIGN_KEY)
            .parseClaimsJws(token)
            .getBody();
        String userId = claims.get("userId").toString();
        return userId;
    }

    @Override
    public Payload getPayloadFromJWT(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SIGN_KEY)
            .parseClaimsJws(token)
            .getBody();
        String userPk =  claims.get("aud").toString();
        String userId = claims.get("userId").toString();
        String role = claims.get("role").toString();
        return new Payload(
            userPk, 
            userId, 
            role
        );
    }
    
}
