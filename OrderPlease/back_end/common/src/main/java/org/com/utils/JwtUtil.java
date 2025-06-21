package org.com.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJwt(String secretKey, long ttlMillis, Map<String, Object> claims){
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalArgumentException("密钥长度必须≥32字符");
        } else if (ttlMillis <= 0) {
            throw new IllegalArgumentException("有效期必须为正数");
        }

        long expMills = System.currentTimeMillis() + ttlMillis;
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        JwtBuilder builder = Jwts.builder().signWith(key).claims(claims).expiration(new Date(expMills));

        return builder.compact();
    }
    /**
     * Token解密
     *
     * @param secretKey jwt 秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则 sign 就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的 token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        JwtParser jwtParser = Jwts.parser().verifyWith(key).build();

        return jwtParser.parseSignedClaims(token).getPayload();
    }
}
