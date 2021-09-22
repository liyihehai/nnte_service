package com.nnte.stockData.util;

import com.nnte.basebusi.excption.BusiException;
import com.nnte.framework.base.BaseNnte;

import java.io.RandomAccessFile;

public class ByteUtils {
    /**
     * int到byte[] 由高位到低位
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }

    /**
     * byte[]转int
     * @param bytes 需要转换成int的数组
     * @return int值
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value=0;
        for(int i = 0; i < 4; i++) {
            int shift= (3-i) * 8;
            value +=(bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    public static int readIntFromRandomAccessFile(RandomAccessFile fis) throws BusiException {
        if (fis==null)
            throw new BusiException("文件输入流为null");
        try {
            int intSizeOf = Integer.SIZE / 8;
            byte[] buf = new byte[intSizeOf];
            int length = fis.read(buf);
            if (length != intSizeOf)
                throw new BusiException("读取int数据失败");
            return ByteUtils.byteArrayToInt(buf);
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }

    public static void writeIntToRandomAccessFile(RandomAccessFile fos,int val) throws BusiException {
        if (fos==null)
            throw new BusiException("文件输出流为null");
        try {
            byte[] buf = ByteUtils.intToByteArray(val);
            fos.write(buf);
        }catch (Exception e){
            throw new BusiException(BaseNnte.getExpMsg(e));
        }
    }
}
