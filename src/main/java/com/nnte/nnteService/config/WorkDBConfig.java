package com.nnte.nnteService.config;

import com.nnte.basebusi.entity.DBSrcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "work.dbsrc")
@PropertySource(value = "classpath:work-dbsrc-config.properties")
@Component
public class WorkDBConfig extends DBSrcConfig {
    public final static String DB_NAME = "esc1-psql-nnte-service"; //定义数据源名称
    public final static String MAPPER_PATH = "com.nnte.pf_business.mapper.workdb";    //扫描路径
}
