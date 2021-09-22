package com.nnte.stockData.component;

import com.nnte.basebusi.excption.BusiException;

/**
 * 股票数据接口,每个股票数据组件需实现该接口
 * */
public interface StockDataInterface {
    /**
     * 返回组件名称
     * */
    String getComponentName();
    /**
     * 组件初始化
     * */
    void OnDataCopmonentInit() throws BusiException;
    /**
     * 组件下载日线数据
     * */
    void OnDownloadStocksDayData(String param) throws BusiException;
}
