package com.gmail.syahputrareno975.customdatepickerlib.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.gmail.syahputrareno975.customdatepickerlib.obj.DatePickerObj;
import com.gmail.syahputrareno975.customdatepickerlib.obj.DayObj;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class CustomDatePickerRes {

    @SuppressLint("SimpleDateFormat")
    public static DatePickerObj SetDateToNow(){
        DatePickerObj date = new DatePickerObj();
        Calendar cal = Calendar.getInstance();


        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH,cal.get(Calendar.MONTH));
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);

        while (month == cal.get(Calendar.MONTH)) {
            DayObj day = new DayObj();
            day.day = cal.get(Calendar.DAY_OF_MONTH);

            date.days.add(day);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        date.month = cal.get(Calendar.MONTH)-1;
        date.year = cal.get(Calendar.YEAR);

        cal.clear();
        return date.cloneDate();
    }

    @SuppressLint("SimpleDateFormat")
    public static DatePickerObj SetDate(DatePickerObj date){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH,(date.month));
        cal.set(Calendar.YEAR,date.year);

        while ((date.month) == cal.get(Calendar.MONTH)) {
            DayObj day = new DayObj();
            day.day = cal.get(Calendar.DAY_OF_MONTH);

            date.days.add(day);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        cal.clear();
        return date.cloneDate();
    }

    public static ArrayList<String> dayToSimpleList(ArrayList<DayObj> days){
        ArrayList<String> daysString = new ArrayList();
        for (DayObj date : days){
            daysString.add(""+date.day);
        }

        return daysString;
    }

    public static String GetMonthName(int month){
        switch (month){
            case 0 : return "January";
            case 1: return "February";
            case 2 : return "March";
            case 3: return "April";
            case 4 : return "May";
            case 5: return "June";
            case 6 : return "July";
            case 7: return "Agust";
            case 8 : return "September";
            case 9: return "October";
            case 10 : return "November";
            case 11: return "December";
            default: return "";
        }
    }

    public static CharSequence[] MakeYearList(int YearCenter){
        ArrayList<String> yearList = new ArrayList<>();
        int yearCenterClone = YearCenter;

        for (int i=10;i>0;i--){
            yearCenterClone--;
            yearList.add(yearCenterClone+"");
        }

        yearCenterClone = YearCenter;
        for (int i=0;i<10;i++){
            yearCenterClone++;
            yearList.add(yearCenterClone+"");
        }

        yearList.add(YearCenter+"");

        Collections.sort(yearList);

        return yearList.toArray(new CharSequence[yearList.size()]);
    }

    public static AlertDialog OpenYearsList(Context ctx,CharSequence[] years,DialogInterface.OnClickListener listener){
        return new AlertDialog.Builder(ctx).setTitle("Choose Year").setItems(years,listener).setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }
}
