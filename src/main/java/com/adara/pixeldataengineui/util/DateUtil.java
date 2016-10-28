package com.adara.pixeldataengineui.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yzhao on 5/9/16.
 */
public class DateUtil {
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public static String convertDateToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        return date; //15/10/2013
    }

    public static String convertStringToDate(String dateInString) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate;
        String date = null;
        try {
            startDate = df.parse(dateInString);
            date = df.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return date.toString(); //Tue Aug 31 10:20:56 SGT 1982
    }

    /*
    * Reference: https://www.mkyong.com/java/how-to-calculate-date-time-difference-in-java/
    * */
    public static Long DateDifferentComparison(String dateStart, String dateStop) {
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        Long diffSeconds = null;
        Long diff = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            diff = d2.getTime() - d1.getTime();

            diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }
}