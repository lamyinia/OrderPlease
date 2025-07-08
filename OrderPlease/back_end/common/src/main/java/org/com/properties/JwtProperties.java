package org.com.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order-please.jwt")
@ToString
public class JwtProperties {
    private String adminSecretKey;
    private String adminTokenName;
    private long adminTtl;

    private String userSecretKey;
    private String userTokenName;
    private long userTtl;
}
