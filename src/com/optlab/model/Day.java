package com.optlab.model;

import java.time.DayOfWeek;
import java.util.Calendar;

public class Day {
    private int day;
    private DayOfWeek dayOfWeek;

    public Day(int day, DayOfWeek dayOfWeek){

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isWeekday(Calendar calendar){
        if (this.dayOfWeek == DayOfWeek.SATURDAY ||
                this.dayOfWeek  == DayOfWeek.SUNDAY)
            return true;
        else
            return false;
    }
}
