package com.nnte.stockData.component;

import com.nnte.AutoTask.AutoTaskInterface;
import com.nnte.AutoTask.AutoTaskMethod;
import com.nnte.AutoTask.TaskRunTime;
import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.utils.BeanUtils;
import com.nnte.stockData.config.StockConfig;
import com.nnte.stockData.config.StockDataTime;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StockDataComponent implements AutoTaskInterface {

    private StockDataInterface stockDataInterface=null;
    private Map<String,StockDataInterface> stockDataInterfaceMap=new HashedMap();

    @Autowired
    private StockConfig stockConfig;
    @Autowired
    private StockDataTime stockDataTime;
    @Override
    public TaskRunTime getTaskRunTimeConfig(String code) {
        return stockDataTime;
    }

    @Override
    public void onAutoTaskBeanInit() throws BusiException {
        List<StockDataInterface> list=BeanUtils.getInterfaceComponentList(StockDataInterface.class);
        if (list!=null && list.size()>0){
            for(StockDataInterface ins:list) {
                stockDataInterfaceMap.put(ins.getComponentName(),ins);
                if (stockConfig.getStockComponent().equals(ins.getComponentName())){
                    stockDataInterface = ins;
                }
            }
        }
        if (stockDataInterface!=null)
            stockDataInterface.OnDataCopmonentInit();
    }

    @AutoTaskMethod(value = "downloadStocksDayData",name = "下载特定股票日线数据")
    public void downloadStocksDayData(String param) throws BusiException{
        if (stockDataInterface!=null)
            stockDataInterface.OnDownloadStocksDayData(param);
    }
}
