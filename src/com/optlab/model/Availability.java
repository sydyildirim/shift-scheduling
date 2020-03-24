package com.optlab.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Availability {

    private static final Availability instance = new Availability();

    //Availability Controls with HashMap
    HashMap<Doctor, List<Day>> notAvailableDays4Doc = new HashMap<>();

    public static Availability getInstance(){
        return instance;
    }

    public boolean isAvailable(Doctor doctor, Day shiftDay){
        if (!notAvailableDays4Doc.containsKey(doctor))
            return true;
        else {
            return !notAvailableDays4Doc.get(doctor).contains(shiftDay);
        }
    }

    //TODO: Can be used also for doctor criteria of dayThatCannotWork
    public void updateAvailability(Doctor doctor, Day shiftDay){
        //The doctor has not been added to the availability map yet
        if (!notAvailableDays4Doc.containsKey(doctor)){
            List<Day> notAvailableDaysList = new ArrayList<>();
            notAvailableDaysList.add(shiftDay);
            notAvailableDays4Doc.put(doctor, notAvailableDaysList);

        }else {
            //If day is already added that means we need to remove that day
            if (notAvailableDays4Doc.get(doctor).contains(shiftDay)){
                notAvailableDays4Doc.get(doctor).remove(shiftDay);
                //We will added the day
            }else {
                notAvailableDays4Doc.get(doctor).add(shiftDay);
            }
        }
    }

    public void clear(){
        notAvailableDays4Doc.clear();
    }

}
