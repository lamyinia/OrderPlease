package org.com.config;

import lombok.extern.slf4j.Slf4j;
import org.com.properties.AliYunProperties;
import org.com.utils.AliYunOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliYunOssUtil aliYunOssUtil(AliYunProperties aliYunProperties){
        log.info("配置阿里云 oss 存储...");
        return new AliYunOssUtil(aliYunProperties.getEndpoint(),
                aliYunProperties.getAccessKeyId(),
                aliYunProperties.getAccessKeySecret(),
                aliYunProperties.getBucketName());
    }
}
