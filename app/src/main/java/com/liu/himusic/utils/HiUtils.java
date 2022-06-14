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
}
