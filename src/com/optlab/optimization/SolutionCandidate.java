package com.optlab.optimization;

import com.optlab.model.Day;
import com.optlab.model.Doctor;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class SolutionCandidate implements Cloneable{

    private Hashtable<Day, List<Doctor>> day2Doc;
    private Hashtable<Doctor, List<Day>> doc2Day;

    public SolutionCandidate() {
        this.day2Doc = new Hashtable<>();
        this.doc2Day = new Hashtable<>();
    }

    //TODO: implement desune
    public double calculateScore(){
        double cost = 0;

        return cost;
    }

    //TODO: suan antcolonySystem.java da gerceklendi.
    public boolean isFeasible(){

        return false;
    }

    public void addDoctors2Day(Day day, List<Doctor> doctors){
        day2Doc.put(day, doctors);
    }

    public Hashtable<Day, List<Doctor>> getDay2Doc() {
        return day2Doc;
    }

    public void setDay2Doc(Hashtable<Day, List<Doctor>> day2Doc) {
        this.day2Doc = day2Doc;
    }

    public int getNumOfShiftsOfDoctor(Doctor doctor){
        return this.doc2Day.get(doctor).size();
    }

    public int getNumOfShiftPerWeekend(Doctor doctor){
        List<Day> dayList = this.doc2Day.get(doctor);
        return (int) dayList.stream().distinct().filter(day -> day.isWeekend()).count();
    }

    public int getNumOfDocOfShift(Day shiftDay){
        return this.day2Doc.get(shiftDay).size();
    }
}
