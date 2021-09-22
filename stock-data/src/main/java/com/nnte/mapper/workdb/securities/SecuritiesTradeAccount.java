package com.nnte.mapper.workdb.securities;
import com.nnte.framework.base.BaseModel;
import com.nnte.framework.annotation.DBPKColum;

import java.util.Date;
/*
 * 自动代码 请勿更改 <2021-02-07 21:11:29>
 */
public class SecuritiesTradeAccount extends BaseModel {
    @DBPKColum private Integer id;
    private String securityMerchant;
    private String clientAccount;
    private Double totalAsset;
    private Double buyingPower;
    private Double securityAsset;
    private Double fundBuyAmount;
    private Double fundBuyFee;
    private Double fundSellAmount;
    private Double fundSellFee;
    private Double withholdingAmount;
    private String accountType;
    private Double frozenMargin;
    private Double frozenExecCash;
    private Double frozenExecFee;
    private Double payLater;
    private Double preadvaPay;
    private Double origBanlance;
    private Double banlance;
    private Double depositWithdraw;
    private Double tradeNetting;
    private Double captialAsset;
    private Double forceFreezeAmount;
    private Double preferredAmount;
    private Double repayStockAvalBanlance;
    private Long unknown;
    private Date createTime;
    private Date updateDate;

    public SecuritiesTradeAccount(){}

    public Integer  getId(){ return id;}
    public void setId(Integer  id){ this.id = id;}
    public String  getSecurityMerchant(){ return securityMerchant;}
    public void setSecurityMerchant(String  securityMerchant){ this.securityMerchant = securityMerchant;}
    public String  getClientAccount(){ return clientAccount;}
    public void setClientAccount(String  clientAccount){ this.clientAccount = clientAccount;}
    public Double  getTotalAsset(){ return totalAsset;}
    public void setTotalAsset(Double  totalAsset){ this.totalAsset = totalAsset;}
    public Double  getBuyingPower(){ return buyingPower;}
    public void setBuyingPower(Double  buyingPower){ this.buyingPower = buyingPower;}
    public Double  getSecurityAsset(){ return securityAsset;}
    public void setSecurityAsset(Double  securityAsset){ this.securityAsset = securityAsset;}
    public Double  getFundBuyAmount(){ return fundBuyAmount;}
    public void setFundBuyAmount(Double  fundBuyAmount){ this.fundBuyAmount = fundBuyAmount;}
    public Double  getFundBuyFee(){ return fundBuyFee;}
    public void setFundBuyFee(Double  fundBuyFee){ this.fundBuyFee = fundBuyFee;}
    public Double  getFundSellAmount(){ return fundSellAmount;}
    public void setFundSellAmount(Double  fundSellAmount){ this.fundSellAmount = fundSellAmount;}
    public Double  getFundSellFee(){ return fundSellFee;}
    public void setFundSellFee(Double  fundSellFee){ this.fundSellFee = fundSellFee;}
    public Double  getWithholdingAmount(){ return withholdingAmount;}
    public void setWithholdingAmount(Double  withholdingAmount){ this.withholdingAmount = withholdingAmount;}
    public String  getAccountType(){ return accountType;}
    public void setAccountType(String  accountType){ this.accountType = accountType;}
    public Double  getFrozenMargin(){ return frozenMargin;}
    public void setFrozenMargin(Double  frozenMargin){ this.frozenMargin = frozenMargin;}
    public Double  getFrozenExecCash(){ return frozenExecCash;}
    public void setFrozenExecCash(Double  frozenExecCash){ this.frozenExecCash = frozenExecCash;}
    public Double  getFrozenExecFee(){ return frozenExecFee;}
    public void setFrozenExecFee(Double  frozenExecFee){ this.frozenExecFee = frozenExecFee;}
    public Double  getPayLater(){ return payLater;}
    public void setPayLater(Double  payLater){ this.payLater = payLater;}
    public Double  getPreadvaPay(){ return preadvaPay;}
    public void setPreadvaPay(Double  preadvaPay){ this.preadvaPay = preadvaPay;}
    public Double  getOrigBanlance(){ return origBanlance;}
    public void setOrigBanlance(Double  origBanlance){ this.origBanlance = origBanlance;}
    public Double  getBanlance(){ return banlance;}
    public void setBanlance(Double  banlance){ this.banlance = banlance;}
    public Double  getDepositWithdraw(){ return depositWithdraw;}
    public void setDepositWithdraw(Double  depositWithdraw){ this.depositWithdraw = depositWithdraw;}
    public Double  getTradeNetting(){ return tradeNetting;}
    public void setTradeNetting(Double  tradeNetting){ this.tradeNetting = tradeNetting;}
    public Double  getCaptialAsset(){ return captialAsset;}
    public void setCaptialAsset(Double  captialAsset){ this.captialAsset = captialAsset;}
    public Double  getForceFreezeAmount(){ return forceFreezeAmount;}
    public void setForceFreezeAmount(Double  forceFreezeAmount){ this.forceFreezeAmount = forceFreezeAmount;}
    public Double  getPreferredAmount(){ return preferredAmount;}
    public void setPreferredAmount(Double  preferredAmount){ this.preferredAmount = preferredAmount;}
    public Double  getRepayStockAvalBanlance(){ return repayStockAvalBanlance;}
    public void setRepayStockAvalBanlance(Double  repayStockAvalBanlance){ this.repayStockAvalBanlance = repayStockAvalBanlance;}
    public Long  getUnknown(){ return unknown;}
    public void setUnknown(Long  unknown){ this.unknown = unknown;}
    public Date  getCreateTime(){ return createTime;}
    public void setCreateTime(Date  createTime){ this.createTime = createTime;}
    public Date  getUpdateDate(){ return updateDate;}
    public void setUpdateDate(Date  updateDate){ this.updateDate = updateDate;}
}
