package org.com.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order-please.wechat")
@ToString
public class WechatProperties {
    private String appid;
    private String appSecret;
}
