package com.optlab.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int id;
    private DoctorTitle title;
    private Department department;
    private List<Constraint> constraintList;
    private List<Wish> wishList;

    private int shiftScore;
    private int weekendShiftScore;
    private int holidayShiftScore;

    public Doctor(){
        this.constraintList = new ArrayList<>();
        this.wishList = new ArrayList<>();
    }

    //calculate score if the doctor has the shift day
    public double calculateScore4ShiftDay(Day shiftDay){
        return 0;
    }

    public DoctorTitle getTitle() {
        return title;
    }

    public void setTitle(DoctorTitle title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addConstraintToConstraintList(Constraint constraint){
        this.constraintList.add(constraint);
    }

    public void addWishToWishList(Wish wish){
        this.wishList.add(wish);
    }

    public void removeConstraintFromConstraintList(Constraint constraint){
        this.constraintList.remove(constraint);
    }

    public void removeWishFromWishList(Wish wish){
        this.wishList.remove(wish);
    }

    public List<Constraint> getConstraintList() {
        return constraintList;
    }

    public void setConstraintList(List<Constraint> constraintList) {
        this.constraintList = constraintList;
    }

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }

    public int getShiftScore() {
        return shiftScore;
    }

    public void setShiftScore(int shiftScore) {
        this.shiftScore = shiftScore;
    }

    public int getWeekendShiftScore() {
        return weekendShiftScore;
    }

    public void setWeekendShiftScore(int weekendShiftScore) {
        this.weekendShiftScore = weekendShiftScore;
    }

    public int getHolidayShiftScore() {
        return holidayShiftScore;
    }

    public void setHolidayShiftScore(int holidayShiftScore) {
        this.holidayShiftScore = holidayShiftScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
