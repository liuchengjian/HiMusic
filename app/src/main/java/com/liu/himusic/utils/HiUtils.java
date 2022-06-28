package com.liu.himusic.utils;

public class HiUtils {

    public static String watchNum(long count) {
        String timeStr = "";
        if (count == 0) {
            return timeStr;
        } else if (count < 10000) {
            return String.valueOf(count);
        } else if (count < 999 * 100000) {
            long start = count / 10000;
            long end = count % 10000 / 1000;
            if (end == 0) {
                timeStr = start + "万";
            } else {
                timeStr = start + "." + end + "万";
            }
            return timeStr;
        } else if (count <= 999 * 100000000) {
            long start1 = count / 100000000;
            long end1 = count % 100000000 % 1000000;
            if (end1 == 0) {
                timeStr = start1 + "亿";
            } else {
                String s = String.valueOf(end1);
                timeStr = start1 + "." + s.charAt(0) + "亿";
            }
            //return "999万+";
        } else if (count > 999 * 100000000) {
            return "999.9亿+";
        }
        return timeStr;
    }

    /**
     * Android 音乐播放器应用里，读出的音乐时长为 long 类型以毫秒数为单位，例如：将 234736 转化为分钟和秒应为 03:55 （包含四舍五入）
     * @param duration 音乐时长
     * @return
     */
    public static String songTimeParse(long duration) {
        String time = "" ;
        long minute = duration / 60000 ;
        long seconds = duration % 60000 ;
        long second = Math.round((float)seconds/1000) ;
        if( minute < 10 ){
            time += "0" ;
        }
        time += minute+":" ;
        if( second < 10 ){
            time += "0" ;
        }
        time += second ;
        return time ;
    }

    /**
     * 毫秒转分秒
     */
    public static String formatTime(long time) {
        String min = (time / (1000 * 60)) + "";
        String second = (time % (1000 * 60) / 1000) + "";
        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + ":" + second;
    }
}
