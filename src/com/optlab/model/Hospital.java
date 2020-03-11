package com.optlab.model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private List<Constrain> constraintList;
    private List<Wish> wishList;

    public Hospital(){
        constraintList = new ArrayList<>();
        wishList = new ArrayList<>();
    }
}
