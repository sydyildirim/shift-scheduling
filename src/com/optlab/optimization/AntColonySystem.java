package com.optlab.optimization;

import com.optlab.Main;
import com.optlab.model.*;

import java.util.*;
import java.util.stream.Collectors;

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

        antCount = antList.size();

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

        double bestScore = Double.MIN_VALUE * -1;
        SolutionCandidate bestSolution = null;

        //Construct Initial Solutions
        for(int iteration =0; iteration<1; iteration++) {
            //Each ant construct a solution
            for (int antIndex = 0; antIndex < antCount; antIndex++) {
                SolutionCandidate newSolutionCandidate = new SolutionCandidate();
                do{
                    Availability.getInstance().clear();
                    newSolutionCandidate.clear();
                    for (Day day: this.shiftCalendar.getDays()) {
                        List<Doctor> selectedDoctors = selectDoctorsAndUpdateAvailability(newSolutionCandidate, day);
                        newSolutionCandidate.addDay2Doctors(day, selectedDoctors);
                    }

                    //Check doctors constraints try couple of times to meet the criteria
                    for (int tryCount = 0; !meetCriteriaOfDoctors(newSolutionCandidate) && (tryCount < 1000); tryCount++);

                }while (!isSolutionFeasible(newSolutionCandidate));

                System.out.println(" isFeasible: " + isSolutionFeasible(newSolutionCandidate));
                printSolution(newSolutionCandidate);

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
                do{
                    Availability.getInstance().clear();
                    newSolutionCandidate.clear();
                    for (Day day: this.shiftCalendar.getDays()) {
                        List<Doctor> selectedDoctors = selectDoctorsAndUpdateAvailability(newSolutionCandidate, day);
                        newSolutionCandidate.addDay2Doctors(day, selectedDoctors);
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

    private void printSolution(SolutionCandidate newSolutionCandidate) {
        System.out.println("Solution: ");
        for (Day day: this.shiftCalendar.getDays()){
            if (day.getDayOfWeek() == Calendar.MONDAY)
                System.out.println("");
            System.out.print("Day :" + day.getDay() + " Doc :");
            for (Doctor doctor: newSolutionCandidate.getDay2Doc().get(day))
                System.out.print(doctor.getId() + " ");
            System.out.print(" | ");
        }
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

    private List<Doctor> selectDoctorsAndUpdateAvailability(SolutionCandidate solutionCandidate, Day shiftDay){
        List<Doctor> selectedDoctors = selectDoctors(solutionCandidate, shiftDay);
        //Update availability of the selected doctors
        for (Doctor doctor: selectedDoctors)
            Availability.getInstance().updateAvailability(doctor, shiftDay);

        return selectedDoctors;
    }

    private List<Doctor> selectDoctors(SolutionCandidate solutionCandidate, Day shiftDay){
        List<Doctor> selectedDoctors = findFeasibleDoctors4ShiftDay(shiftDay);
        //TODO: implement desune

        //Add doctor according to the needed number of doctors of the shift day
        int numOfNeededDoctors = (int) this.hospital.getCriteriaValueByCriteria(Criteria.minNumDoctorPerShift);
        if (solutionCandidate.getDay2Doc().contains(shiftDay))
            numOfNeededDoctors -= solutionCandidate.getDay2Doc().get(shiftDay).size();

        //TODO: do we need something to check shift day shortages?
        if (numOfNeededDoctors >= selectedDoctors.size()){
            return selectedDoctors;

        }else { //Remove extra doctors randomly
            while (numOfNeededDoctors != selectedDoctors.size()){
                selectedDoctors.remove(selectRandomDoctor(selectedDoctors));
            }
            return selectedDoctors;
        }
    }

    private List<Doctor> findFeasibleDoctors4ShiftDay(Day shiftDay){
        List<Doctor> doctors = new ArrayList<>();

        //Look for availability
        for (Doctor doctor: this.doctorList){
            if (Availability.getInstance().isAvailable(doctor, shiftDay)) {
                doctors.add(doctor);
            }
        }

        return doctors;
    }

    private Doctor selectRandomDoctor(){
        //Random rand = new Random();
        Random rand = Main.rng;
        return this.doctorList.get(rand.nextInt(this.doctorList.size()));
    }

    private Doctor selectRandomDoctor(List<Doctor> doctors){
        //Random rand = new Random();
        Random rand = Main.rng;
        return doctors.get(rand.nextInt(doctors.size()));
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
            case shiftThat2DayInARow:
                return checkShiftThat2DayInARow(solutionCandidate, doctor);

                //TODO: implement
            case maxShiftNumPerMonth:
            case maxShiftNumPerWeekend:
                return true;

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
            case maxNumDoctorPerShift:
                return true;
            default:
                //TODO: add log
                return false;
        }
    }

    //TODO: Need to think a more generic way
    private boolean meetCriteriaOfDoctors(SolutionCandidate solutionCandidate){
        for (Doctor doctor: this.doctorList){
            //TODO: firstly check minShiftNumPerWeekend to optimize
            for (Constraint constraint: doctor.getConstraintList()){
                if(!checkCriteria(constraint, solutionCandidate, doctor)){
                    meetCriteria(constraint, solutionCandidate, doctor);
                }
            }
        }

        return isSolutionFeasible(solutionCandidate);
    }

    //TODO: Need to think a more generic way
    private void meetCriteria(Constraint constraint, SolutionCandidate solutionCandidate, Doctor doctor){
        List<Doctor> doctorList;
        switch (constraint.getCriteria()){
            case minShiftNumPerWeekend:
                doctorList = new ArrayList<>();
                doctorList.add(doctor);
                solutionCandidate.addDay2Doctors(selectDayAndUpdateAvailability(doctor, true), doctorList);
                break;
            case minShiftNumPerMonth:
                doctorList = new ArrayList<>();
                doctorList.add(doctor);
                solutionCandidate.addDay2Doctors(selectDayAndUpdateAvailability(doctor, false), doctorList);
                break;
            case shiftThat2DayInARow:
                switchShiftDay(solutionCandidate, doctor);
                break;
        }
    }

    private boolean checkShiftThat2DayInARow(SolutionCandidate solutionCandidate, Doctor doctor){
        if (solutionCandidate.getDoc2Day().containsKey(doctor)){
            List<Day> shiftDays = solutionCandidate.getDoc2Day().get(doctor);
            for (Day shiftDay: shiftDays){
                if (shiftDays.stream().filter(day -> day.getDay() == (shiftDay.getDay()+1)).count() != 0)
                    return false;
                if (shiftDays.stream().filter(day -> day.getDay() == (shiftDay.getDay()-1)).count() != 0)
                    return false;
            }
        }

        return true;
    }

    private void switchShiftDay(SolutionCandidate solutionCandidate, Doctor doctor){
        if (solutionCandidate.getDoc2Day().containsKey(doctor)){
            List<Day> shiftDays = solutionCandidate.getDoc2Day().get(doctor);
            for (Day shiftDay: shiftDays){
                if (shiftDays.stream().filter(day -> day.getDay() == (shiftDay.getDay()+1)).count() != 0){
                    //the shiftDay need to be switched
                    switchShiftDaysAndUpdateAvailability(solutionCandidate,doctor,shiftDay);
                    break;
                }

                if (shiftDays.stream().filter(day -> day.getDay() == (shiftDay.getDay()-1)).count() != 0){
                    switchShiftDaysAndUpdateAvailability(solutionCandidate,doctor,shiftDay);
                    break;
                }

            }
        }
    }

    private void switchShiftDaysAndUpdateAvailability(SolutionCandidate solutionCandidate, Doctor doctor, Day previousDay){
        Day selectedSwitchDay = findAnotherShiftDayForSwitch(solutionCandidate, doctor, previousDay);

        Doctor selectedDoctor = selectRandomDoctor(solutionCandidate.getDay2Doc().get(selectedSwitchDay));

        //Selected doctor for switching does not have the previous day as a shift day
        if (!solutionCandidate.getDoc2Day().get(selectedDoctor).contains(previousDay)){
            //Switch operation
            solutionCandidate.getDay2Doc().get(selectedSwitchDay).remove(selectedDoctor);
            solutionCandidate.getDay2Doc().get(previousDay).add(selectedDoctor);

            solutionCandidate.getDay2Doc().get(previousDay).remove(doctor);
            solutionCandidate.getDay2Doc().get(selectedSwitchDay).add(doctor);

            solutionCandidate.getDoc2Day().get(selectedDoctor).remove(selectedSwitchDay);
            solutionCandidate.getDoc2Day().get(selectedDoctor).add(previousDay);

            solutionCandidate.getDoc2Day().get(doctor).remove(previousDay);
            solutionCandidate.getDoc2Day().get(doctor).add(selectedSwitchDay);

            //Update availability
            //remove old
            Availability.getInstance().updateAvailability(selectedDoctor, selectedSwitchDay);
            Availability.getInstance().updateAvailability(doctor, previousDay);
            //add new
            Availability.getInstance().updateAvailability(selectedDoctor, previousDay);
            Availability.getInstance().updateAvailability(doctor, selectedSwitchDay);
        }
    }

    private Day findAnotherShiftDayForSwitch(SolutionCandidate solutionCandidate, Doctor doctor, Day previousDay){
        //Consider if the day we need to switch is weekend
        List<Day> available4SwitchDayList
                = solutionCandidate.getDay2Doc().keySet().stream()
                .filter(day -> (day != previousDay)
                        && (day.getDay() != previousDay.getDay() - 1)
                        && (day.getDay() != previousDay.getDay() + 1)
                        && !(solutionCandidate.getDay2Doc().get(day).contains(doctor))
                        && (day.isWeekend() == previousDay.isWeekend()))
                .collect(Collectors.toList());
       return selectRandomShiftDay(available4SwitchDayList);
    }

    private Day selectDayAndUpdateAvailability(Doctor doctor, boolean isWeekend){
        Day selectedShiftDay = selectDay(doctor, isWeekend);
        //Update availability
        Availability.getInstance().updateAvailability(doctor, selectedShiftDay);

        return selectedShiftDay;
    }

    private Day selectDay(Doctor doctor, boolean isWeekend){
        List<Day> availableShiftDays = findFeasibleShiftDays4Doctor(doctor, isWeekend);

        return selectRandomShiftDay(availableShiftDays);
    }

    private List<Day> findFeasibleShiftDays4Doctor(Doctor doctor, boolean isWeekend){
        List<Day> shiftDays = new ArrayList<>();

        List<Day> dayList;
        if (isWeekend)
            dayList = this.shiftCalendar.getDays().stream().filter(day -> day.isWeekend()).collect(Collectors.toList());
        else
            dayList = this.shiftCalendar.getDays();

        //Look for availability
        for (Day day: dayList){
            if ( Availability.getInstance().isAvailable(doctor, day))
                shiftDays.add(day);
        }

        return shiftDays;
    }

    private Day selectRandomShiftDay(List<Day> availableShiftDays){
        //Random random = new Random();
        Random random = Main.rng;
        return availableShiftDays.get(random.nextInt(availableShiftDays.size()));
    }
}
