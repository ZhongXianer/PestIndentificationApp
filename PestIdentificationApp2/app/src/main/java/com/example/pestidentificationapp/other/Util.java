package com.example.pestidentificationapp.other;

import java.util.Calendar;

public class Util {

    public static String SavedListName = "history";
    public static String ShpName = "MySharedPreference";

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new String(year + "/" + month + "/" + day);
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return new String(hour + ":" + minute + ":" + second);
    }
}
