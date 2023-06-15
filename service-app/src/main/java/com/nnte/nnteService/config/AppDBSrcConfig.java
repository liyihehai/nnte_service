package com.nnte.nnteService.config;

import com.nnte.basebusi.annotation.RootConfigProperties;
import com.nnte.basebusi.entity.DBSrcConfig;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@RootConfigProperties(fileName = "work-dbsrc-config.properties",prefix = "work.dbsrc",superSet = true)
@Data
public class AppDBSrcConfig extends DBSrcConfig {
    public final static String DB_Name = "NnteServiceDB";
}
