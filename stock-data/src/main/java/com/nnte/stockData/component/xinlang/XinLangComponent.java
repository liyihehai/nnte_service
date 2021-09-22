package com.nnte.stockData.component.xinlang;

import com.nnte.basebusi.annotation.BusiLogAttr;
import com.nnte.basebusi.base.BaseBusiComponent;
import com.nnte.basebusi.excption.BusiException;
import com.nnte.stockData.component.StockDataInterface;
import org.springframework.stereotype.Component;

@Component
@BusiLogAttr(XinLangComponent.componentName)
public class XinLangComponent extends BaseBusiComponent implements StockDataInterface {
    public static final String componentName = "xinlang-stockData";

    @Override
    public String getComponentName() {
        return componentName;
    }

    @Override
    public void OnDataCopmonentInit() throws BusiException {

    }

    @Override
    public void OnDownloadStocksDayData(String param) throws BusiException {

    }
}
