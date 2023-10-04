package com.qa.test.development.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final int wait=10;

    public static String dateAndTime(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date=new Date();
        return dateFormat.format(date);
    }
}
