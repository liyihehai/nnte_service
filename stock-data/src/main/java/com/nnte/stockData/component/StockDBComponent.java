package com.nnte.stockData.component;

import com.nnte.basebusi.annotation.BusiLogAttr;
import com.nnte.basebusi.base.BaseBusiComponent;
import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.utils.BeanUtils;
import com.nnte.mapper.workdb.securities.SecuritiesTradeAccount;
import com.nnte.mapper.workdb.securities.SecuritiesTradeAccountService;
import com.zts.xtp.trade.model.response.AssetResponse;
import com.zts.xtp.trade.model.response.StockPositionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@BusiLogAttr("StockData")
public class StockDBComponent extends BaseBusiComponent {
    @Autowired
    private SecuritiesTradeAccountService securitiesTradeAccountService;
    /**
     * 刷新券商客户的交易资金数据
     * */
    public void refreshSecuritiesTradeAccount(String securityMerchant,String clientAccount,
                                              AssetResponse assetInfo) throws BusiException{
        SecuritiesTradeAccount stdDto = new SecuritiesTradeAccount();
        stdDto.setSecurityMerchant(securityMerchant);
        stdDto.setClientAccount(clientAccount);
        List<SecuritiesTradeAccount> list = securitiesTradeAccountService.findModelList(stdDto);
        if (list!=null && list.size()>0){
            SecuritiesTradeAccount updateSTA=list.get(0);
            BeanUtils.copyFromSrc(assetInfo,updateSTA);
            updateSTA.setUpdateDate(new Date());
            int count=securitiesTradeAccountService.updateModel(updateSTA);
            if (count!=1)
                throw new BusiException(11000,"更改券商客户交易资金数据失败", BusiException.ExpLevel.ERROR);
        }else{
            //需要重新生成
            SecuritiesTradeAccount addSTA = new SecuritiesTradeAccount();
            BeanUtils.copyFromSrc(assetInfo,addSTA);
            addSTA.setSecurityMerchant(securityMerchant);
            addSTA.setClientAccount(clientAccount);
            addSTA.setCreateTime(new Date());
            addSTA.setRepayStockAvalBanlance(0.0);//融券卖出所得资金余额现在结构体没有返回
            int count=securitiesTradeAccountService.addModel(addSTA);
            if (count!=1)
                throw new BusiException(11001,"新增券商客户交易资金数据失败", BusiException.ExpLevel.ERROR);
        }
    }
    /**
     * 刷新客户持仓
     * */
    public void refreshSecuritiesPosition(String securityMerchant,String clientAccount,
                                          StockPositionResponse stockPositionInfo)throws BusiException{

    }
}
