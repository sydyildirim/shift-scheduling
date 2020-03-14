package com.optlab.model;

import java.time.DayOfWeek;
import java.util.Calendar;

public class Day {
    private int day;
    private int dayOfWeek;

    public Day(int day, int dayOfWeek){
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isWeekday(){
        if (this.dayOfWeek == Calendar.SATURDAY ||
                this.dayOfWeek  == Calendar.SUNDAY)
            return true;
        else
            return false;
    }
}
