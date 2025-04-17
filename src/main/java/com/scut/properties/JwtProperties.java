package com.scut.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scut.jwt")
@Data
public class JwtProperties {
    /**
     * jwt相关配置
     */
    private String secretKey;
    private long ttl;
    private String tokenName;
}
