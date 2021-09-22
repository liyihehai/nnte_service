package com.nnte.stockData.component.zt_xtp;

import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.base.BaseNnte;
import com.nnte.framework.utils.FileUtil;
import com.nnte.stockData.config.StockConfig;
import com.nnte.stockData.entity.TradeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@Component
public class XTPFileComponent {
    @Autowired
    private StockConfig stockConfig;

    private final static String dateFile = "date.xtp";

    public String getDataRootPath(){
        return System.getProperty("user.home") + "/" + stockConfig.getDataRoot() + "/";
    }
    /**
     * 读取指定数量的交易日信息到系统
     * */
    public List<TradeDate> readTradeDate(int count) throws BusiException{
        String dateFileName = getDataRootPath() + "tradeDate/"+dateFile;
        dateFileName = FileUtil.getUNIXfilePath(dateFileName);
        List<TradeDate> retList = new ArrayList<>();
        RandomAccessFile raf=openRandomAccessFile(dateFileName);
        try {
            long existCount = raf.length();///
            int struSize=TradeDate.getSizeOf();
            existCount = existCount / struSize;
            long canReadCount = count;
            if (canReadCount > existCount)
                canReadCount = existCount;
            if (canReadCount>0) {
                raf.seek((existCount - canReadCount) * TradeDate.getSizeOf());
                for (long i = 0; i < canReadCount; i++) {
                    TradeDate tDate = new TradeDate();
                    tDate.readFromFile(raf);
                    retList.add(tDate);
                }
            }
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }finally {
            closeStream(raf);
        }
        return retList;
    }
    /**
     * 取得最大的日期
     * */
    public TradeDate getMaxDate() throws BusiException{
        List<TradeDate> list=readTradeDate(1);
        if (list!=null && list.size()==1)
            return list.get(0);
        return null;
    }
    /**
     * 打开读写随机访问文件
     * */
    private RandomAccessFile openRandomAccessFile(String pathFileName) throws BusiException {
        try{
            RandomAccessFile randomFile = new RandomAccessFile(pathFileName, "rw");
            return randomFile;
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }

    /**
     * 关闭流
     * */
    private void closeStream(Closeable fs) throws BusiException {
        try{
            if (fs!=null)
                fs.close();
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }
    /**
     * 保存一个日期到文件，采用追加方式
     * */
    public void saveTradeDate(TradeDate tDay) throws BusiException{
        if (tDay==null)
            throw new BusiException("待保存的日期对象为null");
        String dateFilePath = getDataRootPath() + "tradeDate";
        dateFilePath=FileUtil.getUNIXfilePath(dateFilePath);
        if (!FileUtil.isPathExists(dateFilePath))
            FileUtil.makePath(dateFilePath);
        String dateFileName = dateFilePath +"/"+dateFile;
        RandomAccessFile raf = null;
        try {
            TradeDate maxDay=getMaxDate();
            if (maxDay!=null && maxDay.getDate()>=tDay.getDate())
                return;
            if (FileUtil.isFileExist(dateFileName)) {
                raf = openRandomAccessFile(dateFileName);
            } else {
                FileUtil.createFile(dateFileName, "");
                raf = openRandomAccessFile(dateFileName);
            }
            tDay.saveToFile(raf);
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }finally {
            closeStream(raf);
        }
    }


}
