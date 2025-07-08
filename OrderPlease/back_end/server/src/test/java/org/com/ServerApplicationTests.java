package org.com;


import lombok.extern.slf4j.Slf4j;
import org.com.properties.JwtProperties;
import org.com.properties.WechatProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerApplicationTests {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    WechatProperties wechatProperties;
    @Test
    void test(){
        log.info(wechatProperties.toString());
    }
}
