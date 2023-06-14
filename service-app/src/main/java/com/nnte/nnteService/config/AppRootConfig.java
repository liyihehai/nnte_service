package com.nnte.nnteService.config;

import com.nnte.basebusi.annotation.RootConfigProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@RootConfigProperties(fileName = "nnte-service-config.properties",prefix = "nnte.service")
@Data
public class AppRootConfig {
    public final static String App_Code="NNTE-SERVICE";
    public final static String App_Name="nnte服务应用";
    //本地工作环境
    private String debug;
}
