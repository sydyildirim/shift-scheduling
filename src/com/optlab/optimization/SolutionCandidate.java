package com.optlab.optimization;

import com.optlab.model.Day;
import com.optlab.model.Doctor;

import java.util.ArrayList;
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

    public void addDay2Doctors(Day day, List<Doctor> doctors){
        if (!day2Doc.containsKey(day)){
            day2Doc.put(day, doctors);

        }else {
            day2Doc.get(day).addAll(doctors);
        }

        //Also add transpose relation
        addDoctor2Day(day, doctors);
    }

    public Hashtable<Day, List<Doctor>> getDay2Doc() {
        return day2Doc;
    }

    public void addDoctor2Day(Day day, List<Doctor> doctors){
        for (Doctor doctor: doctors){
            if (!doc2Day.containsKey(doctor)) {
                List<Day> dayList = new ArrayList<>();
                dayList.add(day);
                doc2Day.put(doctor, dayList);

            }else {
                doc2Day.get(doctor).add(day);
            }
        }
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

    public Hashtable<Doctor, List<Day>> getDoc2Day() {
        return doc2Day;
    }

    public void clear(){
        doc2Day.clear();
        day2Doc.clear();
    }
}
