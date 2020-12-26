package com.nnte.nnteService.config;

import com.nnte.basebusi.entity.AppRegistry;
import org.springframework.stereotype.Component;

@Component
public class AppModelConfig {
    /**
     * 系统模块定义
     * 模块定义为可独立交付给客户的一组功能
     * */
    public final static String MODULE_SYSSETTING = "systemSetting"; //系统设置
    public final static String MODULE_SERVICE_MANAGE = "serviceManage"; //服务管理
    static {
        AppRegistry.setAppModuleName(MODULE_SYSSETTING,"系统设置");
        AppRegistry.setAppModuleName(MODULE_SERVICE_MANAGE,"服务管理");
    }
}
