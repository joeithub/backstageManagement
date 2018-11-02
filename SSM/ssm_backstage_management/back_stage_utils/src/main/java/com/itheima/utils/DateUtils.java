package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转换成字符串
  public static String dateToString(Date date,String pattern){

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      return simpleDateFormat.format(date);
  }
    //字符串转换成日期
    public static Date stringToDate(String s,String pattern) throws ParseException {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(s);
    }
}
