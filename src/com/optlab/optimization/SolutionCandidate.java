package com.optlab.optimization;

import com.optlab.model.Day;
import com.optlab.model.Doctor;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class SolutionCandidate implements Cloneable{

    private Hashtable<Day, List<Doctor>> day2Doc;

    public SolutionCandidate() {
        this.day2Doc = new Hashtable<>();
    }

    //TODO: implement desune
    public double calculateScore(){
        double cost = 0;

        return cost;
    }

    //TODO: implement desune
    public boolean isFeasible(){
        return false;
    }
}
