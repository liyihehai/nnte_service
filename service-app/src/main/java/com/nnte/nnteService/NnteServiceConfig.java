package com.nnte.nnteService;

import com.nnte.basebusi.annotation.AppInitInterface;
import com.nnte.basebusi.base.BaseComponent;
import com.nnte.basebusi.entity.AppRegistry;
import com.nnte.basebusi.entity.MEnter;
import com.nnte.basebusi.entity.SysModule;
import com.nnte.framework.base.DBSchemaPostgreSQL;
import com.nnte.framework.base.DynamicDatabaseSourceHolder;
import com.nnte.framework.base.SpringContextHolder;
import com.nnte.framework.utils.FileUtil;
import com.nnte.nnteService.component.JedisComponent;
import com.nnte.nnteService.config.AppDBSrcConfig;
import com.nnte.task_frame.component.TaskFrameComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NnteServiceConfig extends BaseComponent
        implements ApplicationRunner, AppInitInterface {
    @Autowired
    private DBSchemaPostgreSQL dbSchemaPostgreSQL;
    @Autowired
    private AppDBSrcConfig appDBSrcConfig;
    @Autowired
    private TaskFrameComponent taskFrameComponent;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AppRegistry.registry(NnteServiceApplication.App_Code,
                NnteServiceApplication.App_Name,this);
        appInit();
    }
    private void appInit() throws Exception{
        //--------------------------------------------------------------------------------------
        String jarPath= FileUtil.toUNIXpath(System.getProperty("user.dir"));
        outLogInfo("执行后续初始化功能......");
        //--------------------------------------------------------------------------------------
        DynamicDatabaseSourceHolder ddh= SpringContextHolder.getBean(DynamicDatabaseSourceHolder.class);
        outLogInfo("初始化DynamicDatabaseSourceHolder组件......"+(ddh==null?"null":"suc"));
        ddh.loadDBSchemaInterface();
        //--初始化Redis服务器-----
        JedisComponent jedis = SpringContextHolder.getBean(JedisComponent.class);
        jedis.initJedisCom();
        //-----------------------
        //------------------------
        outLogInfo("初始化工作数据库连接数据源......");
        BaseComponent.createDataBaseSource(dbSchemaPostgreSQL, AppDBSrcConfig.DB_Name,
                true,appDBSrcConfig);
        //--初始化文件服务器连接--
        //--------装载系统模块入口--------------
        BaseComponent.loadSystemFuntionEnters();
        //-------------------------------------
        //--启动服务监控--------------------
        taskFrameComponent.startTaskMonitor();
        //-------------------------------------
    }

    @Override
    public void onRegisterFunctions(String appCode, String appName, Map<String, SysModule> moduleMap, List<MEnter> functionModuleList) {
    }
}
