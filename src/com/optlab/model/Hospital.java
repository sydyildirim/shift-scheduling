package com.optlab.model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private List<Constraint> constraintList;
    private List<Wish> wishList;

    public Hospital(){
        constraintList = new ArrayList<>();
        wishList = new ArrayList<>();
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
}
