package com.optlab;

import com.optlab.model.*;
import com.optlab.optimization.AntColonySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final Random rng = new Random();

    public static void main(String[] args) {

        //799623108
        //7996208
        Main.rng.setSeed(7996208);

        Scanner in = new Scanner(System.in);

        //Get month
        System.out.println("Please enter the month:");
        int month = in.nextInt();
        System.out.println("You entered: "+ month);

        ShiftCalendar shiftCalendar = new ShiftCalendar(month);
        shiftCalendar.printCalendar();

        //Create hospital
        Hospital newHospital = new Hospital();
        newHospital.addConstraintToConstraintList(new Constraint(Criteria.maxNumDoctorPerShift, 4)); //?
        newHospital.addConstraintToConstraintList(new Constraint(Criteria.minNumDoctorPerShift, 1));

        //Get num of doctors
        System.out.println("Please enter the num of doctors:");
        int numOfDoctors = in.nextInt();
        System.out.println("You entered: "+numOfDoctors);

        //Create Doctors
        List<Doctor> doctorList = new ArrayList<>();
        for (int i=0; i<numOfDoctors; i++) {
            Doctor newDoctor = new Doctor();
            newDoctor.setId(i);
            newDoctor.addConstraintToConstraintList(new Constraint(Criteria.maxShiftNumPerWeekend, 4));//?
            newDoctor.addConstraintToConstraintList(new Constraint(Criteria.minShiftNumPerWeekend, 2));
            newDoctor.addConstraintToConstraintList(new Constraint(Criteria.maxShiftNumPerMonth, 10)); //?
            newDoctor.addConstraintToConstraintList(new Constraint(Criteria.minShiftNumPerMonth, 7));
            newDoctor.addConstraintToConstraintList(new Constraint(Criteria.shiftThat2DayInARow, 0));
            doctorList.add(newDoctor);
        }

        //CONSTRAINTS

        //CONSTRAINTS OF HOSPITAL


        //CONSTRAINT OF DOCTORS


        //WISHES
        //WISH OF DOCTORS

        AntColonySystem antColonySystem = new AntColonySystem();
        antColonySystem.initializeAntColonySystem(doctorList, shiftCalendar, newHospital);
        antColonySystem.initializeParameters(10, 10);
        antColonySystem.solve();

    }
}
