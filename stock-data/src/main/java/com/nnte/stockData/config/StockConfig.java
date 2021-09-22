package com.nnte.stockData.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "stock.base")
@PropertySource(value = "classpath:stock-base-config.properties")
@Component
public class StockConfig {
    private String dataRoot;
    private String stockComponent;

    public String getDataRoot() {
        return dataRoot;
    }

    public void setDataRoot(String dataRoot) {
        this.dataRoot = dataRoot;
    }

    public String getStockComponent() {
        return stockComponent;
    }

    public void setStockComponent(String stockComponent) {
        this.stockComponent = stockComponent;
    }
}
