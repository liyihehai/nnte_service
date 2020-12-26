package com.nnte.nnteService;

import com.nnte.basebusi.annotation.AppInitInterface;
import com.nnte.basebusi.base.BaseBusiComponent;
import com.nnte.basebusi.base.JedisComponent;
import com.nnte.basebusi.base.WatchComponent;
import com.nnte.basebusi.entity.AppRegistry;
import com.nnte.basebusi.entity.MEnter;
import com.nnte.framework.base.*;
import com.nnte.framework.utils.BaiduMapUtil;
import com.nnte.nnteService.component.NnteServiceComponent;
import com.nnte.nnteService.config.AppRootConfig;
import com.nnte.nnteService.config.WorkDBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NnteServiceConfig extends BaseBusiComponent
        implements ApplicationRunner, AppInitInterface {

    @Autowired
    private WatchComponent watchComponent;
    @Autowired
    private DBSchemaPostgreSQL dbSchemaPostgreSQL;
    @Autowired
    private WorkDBConfig workDBConfig;
    @Autowired
    private AppRootConfig appRootConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AppRegistry.registry(NnteServiceApplication.App_Code,
                NnteServiceApplication.App_Name,this);
        appInit();
    }

    private void appInit() throws Exception{
        //--------------------------------------------------------------------------------------
        BaseNnte.outConsoleLog("执行后续初始化功能......");
        ConfigInterface.LoadConfigComponent(appRootConfig);
        BaiduMapUtil.setBaiduApiKey(appRootConfig.getConfig("baiduApiKey"));
        //--------------------------------------------------------------------------------------
        //--初始化Redis服务器-----
        JedisComponent jedis = SpringContextHolder.getBean(JedisComponent.class);
        jedis.setLogInterface(this);
        jedis.initJedisCom();
        //-----------------------
        //--初始化文件服务器连接--
        //------------------------
        BaseBusiComponent.logInfo(this,"初始化工作数据库连接......");
        BaseBusiComponent.createDataBaseSource(NnteServiceComponent.class,
                dbSchemaPostgreSQL,WorkDBConfig.DB_NAME,WorkDBConfig.MAPPER_PATH,
                true,workDBConfig);
        //--------装载系统模块入口--------------
        BaseBusiComponent.loadSystemFuntionEnters();
        //-------------------------------------
        //--启动程序守护线程，注册组件（系统参数）
        watchComponent.startWatch();
        //-------------------------------------
    }

    @Override
    public void onRegisterFunctions(String appCode, String appName, Map<String, String> moduleMap, List<MEnter> functionModuleList) {

    }
}
