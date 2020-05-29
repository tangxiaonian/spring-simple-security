package com.tang.spring_security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JwtTokenUtils {

    private static final String PRIVATE_KEY = "7786df7fc3a34e26a61c034d5ec8245d";
    // 过期时间 单位分钟
    private static final Integer EXPIRATION_TIME = 15;

    /**
     * 获取一个token
     *
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {

        // 创建token时间
        claims.put("createTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, PRIVATE_KEY)
                .compact();
    }

    /**
     * 获取一个人token,传入用户名
     *
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return generateToken(map);
    }

    /**
     * 刷新token
     *
     * @param username
     * @return
     */
    public static String refreshToken(String username) {
        return generateToken(username);
    }

    /**
     * 刷新token
     *
     * @param claims
     * @return
     */
    public static String refreshToken(Map<String, Object> claims) {
        return generateToken(claims);
    }

    /**
     * 解析JWTString
     *
     * @param jwtString
     * @return
     */
    public static Claims parseJwt(String jwtString) {
        try {
            return Jwts.parser()
                    .setSigningKey(PRIVATE_KEY)
                    .parseClaimsJws(jwtString).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("token 解析失败....可能过期了...");
            return null;
        }

    }

    /**
     * 解析JWTString,传入 key 返回 value
     *
     * @param jwtString
     * @param key
     * @return
     */
    public static String parseJwtReturnValue(String jwtString, String key) {
        Claims claims = parseJwt(jwtString);
        if (claims != null) {
            return Optional.ofNullable(claims.get(key)).orElse("").toString();
        }
        return "";
    }

    /**
     * 判断token是否过期
     *
     * @param jwtString
     * @return
     */
    public static boolean isTokenExpired(String jwtString) {
        Claims claims = parseJwt(jwtString);
        if (claims != null) {
            // 获取过期时间，验证过期时间
            Date expirationTime = claims.getExpiration();
            // 过期时间是否在 当前时间之前
            return expirationTime.before(new Date());
        }
        return true;
    }

}

