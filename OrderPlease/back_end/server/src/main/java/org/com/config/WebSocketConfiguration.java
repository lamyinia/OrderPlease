package org.com.config;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Slf4j
@Configuration
@ConditionalOnWebApplication  // 添加此注解
public class WebSocketConfiguration {
    @Autowired
    private ServletContext servletContext;

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        if (servletContext == null) return null;

        log.info("配置 WebSocket 服务...");
        return new ServerEndpointExporter();
    }
}
