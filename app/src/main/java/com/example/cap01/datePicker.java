package com.example.cap01;

import java.util.Calendar;
public class datePicker {
    Calendar oCalendar = Calendar.getInstance( );
    public String datepick()
    {
        String year=String.valueOf(oCalendar.get(Calendar.YEAR));
        String month=String.valueOf((oCalendar.get(Calendar.MONTH) + 1));
        if (Integer.parseInt(month)<10)
        {
            month=0+month;
        }
        String day= String.valueOf(oCalendar.get(Calendar.DAY_OF_MONTH));
        if (Integer.parseInt(day)<10)
        {
            day=0+day;
        }

        return year+month+day;
    }
}
