package com.nnte.stockData.component.zt_xtp;

import com.nnte.basebusi.annotation.BusiLogAttr;
import com.nnte.basebusi.base.BaseBusiComponent;
import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.utils.BeanUtils;
import com.nnte.framework.utils.DateUtils;
import com.nnte.framework.utils.ThreadUtil;
import com.nnte.stockData.component.StockDBComponent;
import com.nnte.stockData.component.StockDataInterface;
import com.nnte.stockData.config.XTPUser;
import com.nnte.stockData.entity.QuoteSpiImpl;
import com.nnte.stockData.entity.TradeAccount;
import com.nnte.stockData.entity.TradeDate;
import com.nnte.stockData.entity.TradeSpiImpl;
import com.zts.xtp.common.enums.*;
import com.zts.xtp.common.jni.JNILoadLibrary;
import com.zts.xtp.common.model.ErrorMessage;
import com.zts.xtp.quote.api.QuoteApi;
import com.zts.xtp.quote.model.response.SpecificTickerResponse;
import com.zts.xtp.quote.spi.QuoteSpi;
import com.zts.xtp.trade.api.TradeApi;
import com.zts.xtp.trade.model.response.AssetResponse;
import com.zts.xtp.trade.model.response.StockPositionResponse;
import com.zts.xtp.trade.spi.TradeSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@BusiLogAttr(XTPComponent.componentName)
public class XTPComponent extends BaseBusiComponent implements StockDataInterface {

    public static final String componentName = "zt-xtp-stockData";
    @Autowired
    private StockDBComponent stockDBComponent;
    @Autowired
    private XTPUser xtpUser;
    @Autowired
    private XTPFileComponent xtpFileComponent;

    private static String securityMerchant = "ZTZQ"; //中泰证券
    private static String clientAccount = "";

    private static QuoteApi quoteApi;
    private static boolean loginFlag;
    private static TradeApi tradeApi;
    private static String sessionId;

    private static TradeSpi tradeSpi = new TradeSpiImpl();
    private static AssetResponse assetResponse = new AssetResponse();
    private static TradeAccount tradeAccount = new TradeAccount();
    private static Map<String, SpecificTickerResponse> tickersMap = new HashMap<>();

    public static boolean isLoginFlag() {
        return loginFlag;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static boolean isTradeFlag() {
        return (sessionId!=null && sessionId.length()>0 && !sessionId.equals("0"));
    }

    public boolean QuoteApiInit(short clientId,String dataFolder,String ip,int port,String user,String password){
        QuoteSpi quoteSpi = new QuoteSpiImpl();
        quoteApi = new QuoteApi(quoteSpi);
        quoteApi.init(clientId, dataFolder, XtpLogLevel.XTP_LOG_LEVEL_INFO, JniLogLevel.JNI_LOG_LEVEL_INFO);
        int loginResult = quoteApi.login(ip, port, user, password, TransferProtocol.XTP_PROTOCOL_TCP);
        loginFlag = (loginResult == 0);
        if (loginFlag)
            clientAccount = user;
        return loginFlag;
    }

    public boolean TradeApiInit(short clientId,String key,String dataFolder,String ip,int port,String user,String password){
        tradeApi = new TradeApi(tradeSpi);
        tradeApi.init(clientId, key, dataFolder, XtpLogLevel.XTP_LOG_LEVEL_INFO, JniLogLevel.JNI_LOG_LEVEL_INFO, XtpTeResumeType.XTP_TERT_RESTART);
        sessionId = tradeApi.login(ip, port, user, password, TransferProtocol.XTP_PROTOCOL_TCP);
        return isTradeFlag();
    }

    public String getLastError(){
        String lastError =tradeApi.getApiLastError();
        return lastError;
    }

    public void shutdownQuoteApi() {
        quoteApi.logout();
        quoteApi.disconnect();
    }

    public void shutdownTradeApi() {
        tradeApi.logout(sessionId);
        tradeApi.disconnect();
        loginFlag = false;
        ThreadUtil.Sleep(1000);
    }

    /**
     * 获取交易日期：只有登录成功后,才能得到正确的交易日
     * */
    public String getTradingDay(){
        if (isTradeFlag())
            return tradeApi.getTradingDay();
        return null;
    }
    //获取当前交易日可交易合约
    public void subscribeAllMarketData() throws BusiException{
        if (loginFlag){
            int error=quoteApi.subscribeAllMarketData(ExchangeType.SH.getType());
            if (error!=0){
                throw new BusiException(getLastError());
            }
        }
    }

    public synchronized static void setTickerToMap(SpecificTickerResponse ticker){
        if (ticker!=null){
            tickersMap.put(ticker.getTicker(),ticker);
        }
    }
    //请求查询资产
    public void QueryAsset() throws BusiException{
        if (loginFlag){
            int requestId = 0;
            int error=tradeApi.queryAsset(sessionId,requestId);
            if (error!=0){
                throw new BusiException(getLastError());
            }
        }
    }
    //请求查询资产返回
    public synchronized void onQueryAsset(AssetResponse assetInfo, ErrorMessage errorMessage, String sessionId)
            throws BusiException{
        if (errorMessage!=null && errorMessage.getErrorId()==0) {
            BeanUtils.copyFromSrc(assetInfo, assetResponse);
            stockDBComponent.refreshSecuritiesTradeAccount(securityMerchant,clientAccount,assetInfo);
        }
    }
    //查询持仓
    public void QueryPosition(String ticker) throws BusiException{
        if (loginFlag){
            int requestId = 0;
            int error=tradeApi.queryPosition(ticker,sessionId,requestId);
            if (error!=0){
                throw new BusiException(getLastError());
            }
        }
    }
    //查询持仓返回
    public synchronized void onQueryPosition(StockPositionResponse stockPositionInfo,
                                             ErrorMessage errorMessage, String sessionId)
            throws BusiException{
        if (errorMessage!=null && errorMessage.getErrorId()==0) {
            BeanUtils.copyFromSrc(stockPositionInfo, assetResponse);
            stockDBComponent.refreshSecuritiesPosition(securityMerchant,clientAccount,stockPositionInfo);
        }
    }

    @Override
    public String getComponentName() {
        return componentName;
    }

    @Override
    public void OnDataCopmonentInit() throws BusiException{
        JNILoadLibrary.loadLibrary();
        boolean isQuoteSuc=QuoteApiInit(xtpUser.getClientId(),xtpUser.getLogFolder(),
                xtpUser.getQuoteServerIP(),xtpUser.getQuoteServerPort(),
                xtpUser.getUserName(), xtpUser.getUserPwd());
        BaseBusiComponent.logInfo(this,"初始化StockData QuoteAPI...用户:"+
                xtpUser.getUserName()+",init="+(isQuoteSuc?"true":"false"));
        boolean isTradeSuc=TradeApiInit(xtpUser.getClientId(),xtpUser.getServerKey(),xtpUser.getLogFolder(),
                xtpUser.getTradeServerIP(),xtpUser.getTradeServerPort(),
                xtpUser.getUserName(), xtpUser.getUserPwd());
        String lastError="";
        if (!isTradeSuc)
            lastError=getLastError();
        BaseBusiComponent.logInfo(this,"初始化StockData TradeAPI...用户:"+
                xtpUser.getUserName()+",init="+(isTradeSuc?"true":("false,Error="+lastError)));
    }

    @Override
    public void OnDownloadStocksDayData(String param) throws BusiException{
        BaseBusiComponent.logInfo(this,"启动StockDataComponent...TradeDate="+getTradingDay());
        //----保存交易日期----------
        Date day= DateUtils.stringToDate(getTradingDay(),DateUtils.DF_YMD_STRING_);
        TradeDate tDay = new TradeDate(day);
        xtpFileComponent.saveTradeDate(tDay);
        //----保存股票定义----------
        //subscribeAllMarketData();
        //查询账户资产
        QueryAsset();
        //throw new Exception("测试异常");
    }
}
