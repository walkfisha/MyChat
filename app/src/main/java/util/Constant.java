package util;

import java.text.DecimalFormat;

/**
 * Created by AMOBBS on 2016/11/17.
 */

public class Constant {
    //生成唯一ID码
    public static int getMyId(){
        int id = (int)(Math.random()*1000000);
        return id;
    }

    //other 其它定义，另外消息长度为60个汉字，utf-8中定义一个汉字占3个字节，所以消息长度为180bytes
    //文件长度为30个汉字，所以总长度为90个字节
    public static final int bufferSize = 256;
    public static final int msgLength = 180;
    public static final int fileNameLength = 90;
    public static final int readBufferSize = 4096;//文件读写缓存

    //int to ip转换
    public static String intToIp(int i) {
        String ip = ( (i >> 24) & 0xFF) +"."+((i >> 16 ) & 0xFF)+"."+((i >> 8 ) & 0xFF)+"."+(i & 0xFF );

        return ip;
    }


    //转换文件大小
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = fileS+"B";
            //   fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
