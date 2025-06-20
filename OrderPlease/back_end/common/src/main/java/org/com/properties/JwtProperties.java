package org.com.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order-please.jwt")
public class JwtProperties {
    private String adminSecretKey;
    private String adminTokenName;
    private long adminTtl;
}
