package com.nnte.stockData.entity;

import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.base.BaseNnte;
import com.nnte.framework.utils.DateUtils;
import com.nnte.framework.utils.NumberDefUtil;
import com.nnte.stockData.util.ByteUtils;

import java.io.RandomAccessFile;
import java.util.Date;

public class TradeDate {
    private int date;        //20201230 -- 8
    private int year;        //2020     -- 4
    private int month;       //202012   -- 6
    private int week;        //20201228 -- 8  每周周一的日期
    private int quarter;     //202004   -- 6
    private int halfYear;    //202002   -- 6

    public static int getSizeOf(){
        return Integer.SIZE / 8 * 6;
    }

    public TradeDate() {}
    public TradeDate(Date day) throws BusiException{
        if (day==null)
            throw new BusiException("日期对象不合法");
        date= NumberDefUtil.getDefInteger(DateUtils.dateToString(day,DateUtils.DF_YMD_STRING_));
        year=DateUtils.getYear(day);
        month=NumberDefUtil.getDefInteger(DateUtils.dateToString(day,"yyyyMM"));
        Date weekFirstDay = day;
        int wd=DateUtils.getWeek(day);
        if (wd!=0)
            weekFirstDay=DateUtils.addDay(day,wd * -1);
        week=NumberDefUtil.getDefInteger(DateUtils.dateToString(weekFirstDay,DateUtils.DF_YMD_STRING_));
        int m=DateUtils.getMonth(day);
        int q=1,hy=1;
        if (m<=3) {q = 1;hy=1; }
        else if (4<=m && m<=6) {
            q = 2;hy=1; }
        else if (7<=m && m<=9) {
            q = 3;hy=2;}
        else if (m>=10) {
            q = 4;hy=2; }
        quarter=NumberDefUtil.getDefInteger(String.format("%04d%02d",year,q));
        halfYear=NumberDefUtil.getDefInteger(String.format("%04d%02d",year,hy));
    }

    public void saveToFile(RandomAccessFile raf) throws BusiException{
        try {
            long fileLength = raf.length();
            raf.seek(fileLength);
            ByteUtils.writeIntToRandomAccessFile(raf,date);
            ByteUtils.writeIntToRandomAccessFile(raf,year);
            ByteUtils.writeIntToRandomAccessFile(raf,month);
            ByteUtils.writeIntToRandomAccessFile(raf,week);
            ByteUtils.writeIntToRandomAccessFile(raf,quarter);
            ByteUtils.writeIntToRandomAccessFile(raf,halfYear);
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }

    public void readFromFile(RandomAccessFile raf) throws BusiException{
        try {
            date=ByteUtils.readIntFromRandomAccessFile(raf);
            year=ByteUtils.readIntFromRandomAccessFile(raf);
            month=ByteUtils.readIntFromRandomAccessFile(raf);
            week=ByteUtils.readIntFromRandomAccessFile(raf);
            quarter=ByteUtils.readIntFromRandomAccessFile(raf);
            halfYear=ByteUtils.readIntFromRandomAccessFile(raf);
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getHalfYear() {
        return halfYear;
    }

    public void setHalfYear(int halfYear) {
        this.halfYear = halfYear;
    }
}
