package com.nnte.stockData.config;

import com.nnte.AutoTask.TaskRunTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "stock.data.time")
@PropertySource(value = "classpath:stock-data-config.properties")
@Component
public class StockDataTime extends TaskRunTime {
}
