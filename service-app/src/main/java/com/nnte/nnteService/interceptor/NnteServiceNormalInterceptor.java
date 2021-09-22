package com.nnte.nnteService.interceptor;

import com.nnte.basebusi.base.BaseController;
import com.nnte.basebusi.entity.OperatorInfo;
import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.annotation.ConfigLoad;
import com.nnte.framework.base.BaseNnte;
import com.nnte.framework.base.ConfigInterface;
import com.nnte.framework.utils.StringUtils;
import com.nnte.nnteService.component.NnteServiceComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class NnteServiceNormalInterceptor implements HandlerInterceptor {
    @ConfigLoad
    private ConfigInterface appconfig;
    @Autowired
    private NnteServiceComponent nnteServiceComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求进入这个拦截器
        Map<String, Object> envData = (Map)request.getAttribute("envData");
        BaseNnte.outConsoleLog(request.getServletPath());
        int enterType = 0; //页面进入
        if (envData!=null) {
            Map<String,Object> retMap=BaseNnte.newMapRetObj();
            String loginIp=BaseController.getIpAddr(request);
            String token= StringUtils.defaultString(BaseController.getRequestParam(request,null,"token"));
            if (StringUtils.isEmpty(token)) {
                token = request.getHeader("Postman-Token");
                enterType = 1; //Ajax进入
            }
            try {
                Map checkMap=nnteServiceComponent.checkRequestToken(token, loginIp);
                OperatorInfo oi=(OperatorInfo)checkMap.get("OperatorInfo");
                request.setAttribute("OperatorInfo",oi);
                //如果是进入模块，还需要验证操作员模块权限
                nnteServiceComponent.checkRequestModule(oi,request.getServletPath());
                return true;
            }catch (BusiException be){
                if (be.getExpCode().equals(1010)) {
                    BaseNnte.setRetFalse(retMap, 1010, BaseNnte.getExpMsg(be));
                    retMap.put("message", BaseNnte.getExpMsg(be));
                    retMap.put("enterType",enterType);
                    BaseController.printJsonObject(response,retMap);
                    return false;
                }
                BaseNnte.setRetFalse(retMap,1001,BaseNnte.getExpMsg(be));
            }catch (Exception e){
                BaseNnte.setRetFalse(retMap,1001,"身份验证错误");
            }
            retMap.put("message",retMap.get("msg"));
            retMap.put("enterType",enterType);
            request.setAttribute("map",retMap);
            BaseController.printJsonObject(response,retMap);
            return false;
        }
        return false;        //有的话就继续操作
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
