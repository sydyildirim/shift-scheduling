package com.optlab.data;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShiftCalendar {
    // create a calendar
    Calendar calender = Calendar.getInstance();
    YearMonth yearMonth;
    private List<Day> days;

    public ShiftCalendar(int month){
        this.yearMonth = YearMonth.of(Calendar.YEAR, month);
        this.calender.set(Calendar.YEAR, month-1,1);
        createDays(month);
    }

    private void createDays(int month){
        days = new ArrayList<>();
        for (int i=0; i<yearMonth.lengthOfMonth(); i++){
            Day newDay = new Day(i+1, Calendar.DAY_OF_WEEK);
            days.add(newDay);

        }
    }

    public void printCalendar(){
        System.out.println(calender.getTime());
        System.out.println("days: "+ yearMonth.lengthOfMonth());
    }

    public boolean isWeekday(Calendar calendar){
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return true;
        else
            return false;
    }

    }

