package com.nnte.stockData.entity;

import com.nnte.stockData.component.zt_xtp.XTPComponent;
import com.zts.xtp.common.model.ErrorMessage;
import com.zts.xtp.quote.model.response.*;
import com.zts.xtp.quote.spi.QuoteSpi;

public class QuoteSpiImpl implements QuoteSpi{

    @Override
    public void onDisconnected(int reason) {

    }

    @Override
    public void onError(ErrorMessage errorMessage) {

    }

    @Override
    public void onSubMarketData(SpecificTickerResponse ticker, ErrorMessage errorMessage) {
        if (errorMessage.getErrorId()==0) {
            System.out.println(ticker);
            XTPComponent.setTickerToMap(ticker);
            if (ticker.isLastResp()){
                System.out.println("onSubMarketData finished");
                System.out.println(errorMessage);
            }
        }
    }

    @Override
    public void onUnSubMarketData(SpecificTickerResponse ticker, ErrorMessage errorMessage) {

    }

    @Override
    public void onDepthMarketData(DepthMarketDataResponse depthMarketData,
        DepthMarketDataExResponse depthQuote) {
        System.out.println("onDepthMarketData");
        System.out.println(depthMarketData);
        System.out.println(depthQuote);
    }

    @Override
    public void onSubOrderBook(SpecificTickerResponse ticker, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubOrderBook(SpecificTickerResponse ticker, ErrorMessage errorMessage) {

    }

    @Override
    public void onOrderBook(OrderBookResponse orderBook) {

    }

    @Override
    public void onSubTickByTick(SpecificTickerResponse ticker, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubTickByTick(SpecificTickerResponse ticker, ErrorMessage errorMessage) {

    }

    @Override
    public void onTickByTickEntrust(int exchange_id, String ticker, long seq, long data_time, int type, int channel_no, long order_seq, double price, long qty, char side, char ord_type) {

    }

    @Override
    public void onTickByTickTrade(int exchange_id, String ticker, long seq, long data_time, int type, int channel_no, long order_seq, double price, long qty, double money, long bid_no, long ask_no, char trade_flag) {

    }

    @Override
    public void onSubscribeAllMarketData(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllMarketData(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onSubscribeAllOrderBook(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllOrderBook(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onSubscribeAllTickByTick(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllTickByTick(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onQueryAllTickers(TickerInfoResponse tickerInfo, ErrorMessage errorMessage) {
    }

    @Override
    public void onQueryTickersPriceInfo(TickerPriceInfoResponse tickerInfo, ErrorMessage errorMessage) {

    }

    @Override
    public void onSubscribeAllOptionMarketData(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllOptionMarketData(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onSubscribeAllOptionOrderBook(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllOptionOrderBook(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onSubscribeAllOptionTickByTick(int exchangeId, ErrorMessage errorMessage) {

    }

    @Override
    public void onUnSubscribeAllOptionTickByTick(int exchangeId, ErrorMessage errorMessage) {

    }
}
