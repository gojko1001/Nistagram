package com.nistagram.campaignmicroservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {

    public static Date mergeDateAndTime(Date date, Date time){
        try {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String timeStr = new SimpleDateFormat("HH:mm:ss").format(time);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( dateStr + " " + timeStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date addMinutes(Date date, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date subtractMinutes(Date date, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }

    public static Date getDateFromString(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int calculateAge(Date birthDate){
        LocalDate localDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(localDate, LocalDate.now()).getYears();
    }

    public static List<String> getDayParts(int toDivide){
        int part = 86400000 / toDivide;

        List<String> times = new ArrayList<>();
        for(int i = 1; i <= toDivide; i++){
            int millis = part * i;
            String time = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
            if(time.startsWith("24"))
                time = "00" + time.substring(2);
            times.add(time);
        }
        return times;
    }
}
