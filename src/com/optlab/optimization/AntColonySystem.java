package com.optlab.optimization;

import com.optlab.model.*;

import java.util.ArrayList;
import java.util.Hashtable;
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

                }while (isSolutionFeasible(newSolutionCandidate));

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

                }while (isSolutionFeasible(newSolutionCandidate));
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

    private boolean isSolutionFeasible(SolutionCandidate solutionCandidate){

        //Check constraints of the hospital
        for (Constraint constraint: this.hospital.getConstraintList()) {
            if(!checkCriteria(constraint, solutionCandidate))
                return false;
        }
        //Check constraints of doctors
        for (Doctor doctor: this.doctorList){
            for (Constraint constraint: doctor.getConstraintList()) {
                if(!checkCriteria(constraint, solutionCandidate, doctor))
                    return false;
            }
        }

        return true;
    }

    //TODO: Can implement way better
    private boolean checkCriteria(Constraint constraint, SolutionCandidate solutionCandidate, Doctor doctor){
        switch (constraint.getCriteria()){
            case minShiftNumPerMonth:
                return (solutionCandidate.getNumOfShiftsOfDoctor(doctor)
                        >= constraint.getCriteriaCoefficient());
            case minShiftNumPerWeekend:
                return (solutionCandidate.getNumOfShiftPerWeekend(doctor)
                        >= constraint.getCriteriaCoefficient());
            default:
                //TODO: add log
                return false;
        }
    }

    //TODO: Can implement way better
    private boolean checkCriteria(Constraint constraint, SolutionCandidate solutionCandidate){
        switch (constraint.getCriteria()){
            case minNumDoctorPerShift:
                for (Day day: this.shiftCalendar.getDays()){
                    if (solutionCandidate.getNumOfDocOfShift(day) < constraint.getCriteriaCoefficient())
                        return false;
                }
                return true;
            default:
                //TODO: add log
                return false;
        }
    }
}
