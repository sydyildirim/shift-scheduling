package com.optlab.optimization;

import com.optlab.model.Doctor;
import com.optlab.model.Hospital;
import com.optlab.model.ShiftCalendar;

import java.util.ArrayList;
import java.util.List;

public class AntColonySystem {

    //Parameters
    private int antCount;
    private int maxNumOfIteration;
    private double decayFactor = 0.1;
    private double greedinessFactor = 0.9;
    private double pheromoneCoefficient = 0.1;
    private double heuristicCoefficient = 2.5;
    private double antFactor = 0.8;

    private PheromoneMatrix pheromoneMatrix;
    private DesirabilityMatrix desirabilityMatrix;

    private List<Doctor> doctorList;
    private ShiftCalendar shiftCalendar;
    private Hospital hospital;

    private List<Ant> antList;

    public void initializeAntColonySystem(List<Doctor> doctorList, ShiftCalendar shiftCalendar, Hospital hospital){
        this.doctorList = doctorList;
        this.shiftCalendar = shiftCalendar;
        this.hospital = hospital;
        this.antList = new ArrayList<>();

        for (int i=0; i<shiftCalendar.getDays().size()*antFactor; i++)
            antList.add(new Ant());

    }

    public void initializeParameters(int antCount, int maxNumOfIteration){
        this.antCount = antCount;
        this.maxNumOfIteration = maxNumOfIteration;
    }

    public SolutionCandidate createHeuristicSolution(){

        SolutionCandidate solutionCandidate;

        return null;
    }

    public SolutionCandidate solve(){

        return new SolutionCandidate();
    }

    private void initiliazePheromoneMatrix(){

    }

    private void updatePheromoneMatrix(){

    }

    private void initiliazeHeuristicMatrix(){

    }

    private void updateBestSolution(){

    }

    private void selectDoctor(){

    }


}
