package com.nnte.stockData.entity;

import com.nnte.framework.base.SpringContextHolder;
import com.nnte.stockData.component.zt_xtp.XTPComponent;
import com.zts.xtp.common.model.ErrorMessage;
import com.zts.xtp.trade.model.response.*;
import com.zts.xtp.trade.spi.TradeSpi;

public class TradeSpiImpl implements TradeSpi{

    @Override
    public void onDisconnect(String sessionId, int reason) {

    }

    @Override
    public void onError(ErrorMessage errorMessage) {

    }

    @Override
    public void onFundTransfer(FundTransferResponse fundTransferInfo, ErrorMessage errorMessage, String sessionId) {

    }

    public void onOrderEvent(OrderResponse orderInfo, ErrorMessage errorMessage, String sessionId) {
        System.out.println("onOrderEvent: " + orderInfo);
    }

    @Override
    public void onQueryAsset(AssetResponse assetInfo, ErrorMessage errorMessage, String sessionId) {
        try {
            XTPComponent xtpComponent= SpringContextHolder.getBean(XTPComponent.class);
            xtpComponent.onQueryAsset(assetInfo, errorMessage, sessionId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onQueryETF(ETFBaseResponse etfBaseInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryETFBasket(ETFComponentResponse etfComponentInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryFundTransfer(FundTransferResponse fundTransferInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryIPOInfoList(IPOTickerResponse ipoTickerInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryIPOQuotaInfo(IPOQuotaResponse ipoQuotaInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryOrder(OrderResponse orderInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryOrderByPage(OrderResponse orderInfo, long reqCount, long orderSequence, long queryReference, String sessionId) {

    }

    @Override
    public void onQueryPosition(StockPositionResponse stockPositionInfo, ErrorMessage errorMessage, String sessionId) {
        try {
            XTPComponent xtpComponent= SpringContextHolder.getBean(XTPComponent.class);
            xtpComponent.onQueryPosition(stockPositionInfo, errorMessage, sessionId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onQueryStructuredFund(StructuredFundResponse structuredFundInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryTrade(TradeResponse tradeInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void onQueryTradeByPage(TradeResponse tradeInfo, long reqCount, long tradeSequence, long queryReference, String sessionId) {

    }

    @Override
    public void onTradeEvent(TradeResponse tradeInfo, String sessionId) {
        System.out.println("onTradeEvent: " + tradeInfo);
    }

    @Override
    public void onCancelOrderError(OrderCancelResponse orderCancelInfo, ErrorMessage errorMessage, String sessionId) {

    }

    @Override
    public void OnQueryOptionAuctionInfo(OptionAuctionInfoResponse optionAuctionInfoResponse, ErrorMessage errorMessage, String sessionId) {

    }
}
