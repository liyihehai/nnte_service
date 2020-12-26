package com.nnte.nnteService.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class NnteServiceConfigurer implements WebMvcConfigurer {
    @Autowired
    private NnteServiceMainInterceptor nnteServiceMainInterceptor;
    @Autowired
    private NnteServiceNormalInterceptor nnteServiceNormalInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(nnteServiceMainInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/*/applyVerify","/*/sysRepairing","/*/error",
                        "/*/resources/*");//添加不拦截路径
        //登录拦截的管理器
        registry.addInterceptor(nnteServiceNormalInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/*/applyVerify","/*/sysRepairing",
                        "/*/login","/*/loginCheck","/*/priCheck",
                        "/*/error","/*/resources/*");//添加不拦截路径
    }
}
