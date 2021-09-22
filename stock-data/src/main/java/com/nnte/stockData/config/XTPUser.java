package com.nnte.stockData.config;

import com.zts.xtp.common.enums.TransferProtocol;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "stock.data.user")
@PropertySource(value = "classpath:stock-data-config.properties")
@Component
public class XTPUser {
    private short clientId;
    private String serverKey;
    private String logFolder;
    private String tradeServerIP;
    private int tradeServerPort;
    private String userName;
    private String userPwd;
    private TransferProtocol tradeTransferProtocol;
    private String quoteServerIP;
    private int quoteServerPort;
    private TransferProtocol quoteTransferProtocol;
    private int udpBufferSize;

    public short getClientId() {
        return clientId;
    }

    public void setClientId(short clientId) {
        this.clientId = clientId;
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    public String getLogFolder() {
        return logFolder;
    }

    public void setLogFolder(String logFolder) {
        this.logFolder = logFolder;
    }

    public String getTradeServerIP() {
        return tradeServerIP;
    }

    public void setTradeServerIP(String tradeServerIP) {
        this.tradeServerIP = tradeServerIP;
    }

    public int getTradeServerPort() {
        return tradeServerPort;
    }

    public void setTradeServerPort(int tradeServerPort) {
        this.tradeServerPort = tradeServerPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getQuoteServerIP() {
        return quoteServerIP;
    }

    public void setQuoteServerIP(String quoteServerIP) {
        this.quoteServerIP = quoteServerIP;
    }

    public int getQuoteServerPort() {
        return quoteServerPort;
    }

    public void setQuoteServerPort(int quoteServerPort) {
        this.quoteServerPort = quoteServerPort;
    }

    public int getUdpBufferSize() {
        return udpBufferSize;
    }

    public void setUdpBufferSize(int udpBufferSize) {
        this.udpBufferSize = udpBufferSize;
    }
}
