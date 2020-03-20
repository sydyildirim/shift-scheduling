package com.optlab.optimization;

import com.optlab.model.Day;
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
    double p1 = 0.4;

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
        //this.antCount = antCount;
        this.maxNumOfIteration = maxNumOfIteration;
    }

    public SolutionCandidate createHeuristicSolution(){

        SolutionCandidate solutionCandidate;

        return null;
    }

    public SolutionCandidate solve(){

        double bestScore = Double.MIN_VALUE;
        SolutionCandidate bestSolution = null;

        //Construct Initial Solutions
        for(int iteration =0; iteration<1; iteration++) {
            //Each ant construct a solution
            for (int antIndex = 0; antIndex < antCount; antIndex++) {
                SolutionCandidate newSolutionCandidate = new SolutionCandidate();
                boolean validSolution = true;
                do{
                    for (Day day: this.shiftCalendar.getDays()) {
                        List<Doctor> selectedDoctors = selectDoctors(day);
                        newSolutionCandidate.addDoctors2Day(day, selectedDoctors);
                    }

                }while (newSolutionCandidate.isFeasible());

                double currentScore = newSolutionCandidate.calculateScore();
                if (bestScore <= currentScore){
                    bestScore = currentScore;
                    bestSolution = newSolutionCandidate;
                    //TODO: Consider if a local search can be implemented
                }
            }
        }

        double init_pheromone = (shiftCalendar.getDays().size()*bestScore);
        initializePheromoneMatrix(pheromoneMatrix, shiftCalendar.getDays().size(), bestScore);

        for(int iteration=0; iteration<maxNumOfIteration; iteration++){
            //Each ant construct a solution
            for(int antIndex=0; antIndex<antCount; antIndex++){
                SolutionCandidate newSolutionCandidate = new SolutionCandidate();
                boolean validSolution = true;
                do{
                    for (Day day: this.shiftCalendar.getDays()) {
                        List<Doctor> selectedDoctors = selectDoctors(day);
                        newSolutionCandidate.addDoctors2Day(day, selectedDoctors);
                    }

                }while (newSolutionCandidate.isFeasible());
                double currentScore = newSolutionCandidate.calculateScore();
                if (bestScore <= currentScore){
                    bestScore = currentScore;
                    bestSolution = newSolutionCandidate;
                    //TODO: Consider if a local search can be implemented
                }

                localUpdatePheromoneMatrix(pheromoneMatrix, newSolutionCandidate, p1, init_pheromone);
            }
            globalUpdatePheromoneMatrix(pheromoneMatrix, bestSolution, p1);
        }

        return bestSolution;
    }

    private void initializePheromoneMatrix(PheromoneMatrix pheromoneMatrix, int size, double bestScore){

    }

    private void localUpdatePheromoneMatrix(PheromoneMatrix pheromoneMatrix, SolutionCandidate newSolutionCandidate, double p1, double init_phenemon){

    }

    private void globalUpdatePheromoneMatrix(PheromoneMatrix pheromoneMatrix, SolutionCandidate bestSolution, double p1){

    }

    private void initializeHeuristicMatrix(){

    }

    private void updateBestSolution(){

    }

    private List<Doctor> selectDoctors(Day shiftDay){
        List<Doctor> selectedDoctors = findFeasibleDoctors4ShiftDay(shiftDay);
        //TODO: implement desune
        return selectedDoctors;
    }

    private List<Doctor> findFeasibleDoctors4ShiftDay(Day shiftDay){
        List<Doctor> doctors = new ArrayList<>();
        //TODO: implement desune

        return doctors;
    }


}
