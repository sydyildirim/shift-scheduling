package com.optlab.optimization;

import com.optlab.data.Doctor;
import com.optlab.data.Hospital;
import com.optlab.data.ShiftCalendar;

import java.util.List;

public class AntColonySystem {

    //Parameters
    private int antCount;
    private int maxNumOfIteration;
    private double decayFactor = 0.1;
    private double greedinessFactor = 0.9;
    private double pheromoneCoefficient = 0.1;
    private double heuristicCoefficient = 2.5;

    private PheromoneMatrix pheromoneMatrix;
    private DesirabilityMatrix desirabilityMatrix;

    private List<Doctor> doctorList;
    private ShiftCalendar shiftCalendar;
    private Hospital hospital;

    public void initializeAntColonySystem(List<Doctor> doctorList, ShiftCalendar shiftCalendar, Hospital hospital){
        this.doctorList = doctorList;
        this.shiftCalendar = shiftCalendar;
        this.hospital = hospital;
    }

    public void initializeParameters(int antCount, int maxNumOfIteration){
        this.antCount = antCount;
        this.maxNumOfIteration = maxNumOfIteration;
    }

    public SolutionCandidate createHeuristicSolution(){

        SolutionCandidate solutionCandidate;

        return null;
    }

}
