package com.fuqin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/4/18.
 */
public class DateUtils {

    /**
     * 时间戳转时间
     * @param timestamp
     * @return
     * @throws ParseException
     */
    public Date timestampToDate(long timestamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(timestamp);
        Date date=format.parse(d);
        return date;
    }

    /***
     * 时间转时间戳
     * @param date
     * @return
     */
    public long DateToTimestamp(Date date){
        return date.getTime();
    }

}
