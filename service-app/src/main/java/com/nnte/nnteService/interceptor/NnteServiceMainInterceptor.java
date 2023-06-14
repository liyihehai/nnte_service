package com.nnte.nnteService.interceptor;

import com.nnte.nnteService.config.AppRootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class NnteServiceMainInterceptor implements HandlerInterceptor {
    @Autowired
    private AppRootConfig appRootConfig;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求中添加系统数据环境
        Map<String, Object> envData = new HashMap<>();
        envData.put("contextPath",request.getContextPath());
        envData.put("debug", appRootConfig.getDebug().toUpperCase());
        request.setAttribute("envData",envData);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
