package com.nnte.nnteService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.nnte")
public class NnteServiceApplication
{
    /**
     * 定义应用代码及应用名称
     * */
    public final static String App_Code = "NNTE-SERVICE-MANAGER";
    public final static String App_Name = "NNTE服务管理端应用";
    //------------------------------------------------------------
    public static void main(String[] args)
    {
        SpringApplication.run(NnteServiceApplication.class, args);
    }
}
