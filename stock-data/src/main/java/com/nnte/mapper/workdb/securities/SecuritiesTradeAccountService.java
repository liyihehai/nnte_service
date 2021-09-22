package com.nnte.mapper.workdb.securities;

import com.nnte.framework.base.BaseService;
import org.springframework.stereotype.Component;

@Component
public class SecuritiesTradeAccountService extends BaseService<SecuritiesTradeAccountDao,SecuritiesTradeAccount> {
    public SecuritiesTradeAccountService(){
        super(SecuritiesTradeAccountDao.class);
    }
}

