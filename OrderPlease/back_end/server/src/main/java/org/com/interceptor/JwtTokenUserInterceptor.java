package org.com.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.com.constant.JwtClaimsConstant;
import org.com.context.BaseContext;
import org.com.properties.JwtProperties;
import org.com.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader(jwtProperties.getUserTokenName());
        if (token == null || token.isBlank()){
            response.setStatus(401);
            response.getWriter().write("Missing authorization token");
            return false;
        }

        try {
            log.info("JWT验证 - 令牌: {}...", token.substring(0, Math.min(token.length(), 6)));
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户ID: {}", userId);
            request.setAttribute("currentId", userId);

            BaseContext.setCurrentId(userId);

            return true;
        } catch (ExpiredJwtException ex){
            log.warn("JWT已过期: {}", ex.getMessage());
            response.setStatus(401);
            response.getWriter().write("Token expired");

            return false;
        } catch (JwtException | IllegalArgumentException ex){
            log.warn("无效JWT: {}", ex.getMessage());
            response.setStatus(401);
            response.getWriter().write("Invalid token");

            return false;
        }
    }
}
