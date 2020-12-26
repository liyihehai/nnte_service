package com.nnte.nnteService.config;

import com.nnte.framework.base.ConfigInterface;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nnte.service")
@PropertySource(value = "classpath:nnte-service-config.properties")
@Data
public class AppRootConfig implements ConfigInterface {
    //本地工作环境
    private String debug;
    private String localHostName;
    private String localHostAbstractName;
    private String staticRoot;
    private String uploadStaticRoot;
    //百度API-KEY
    private String baiduApiKey;
}
