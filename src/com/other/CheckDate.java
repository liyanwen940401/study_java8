package com.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckDate {
    public static void  main(String[] args){
        String format = "HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        String now = sf.format(new Date());
        //now = "16:59:59";
        Date nowTime;
        try {
            nowTime = new SimpleDateFormat(format).parse(now);
            Date startTime = new SimpleDateFormat(format).parse("09:00:00");
            Date endTime = new SimpleDateFormat(format).parse("17:00:00");
            if (isEffectiveDate(nowTime, startTime, endTime)) {
                System.out.println("在在这 ");
            } else {
                System.out.println("不在不在 ");
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
