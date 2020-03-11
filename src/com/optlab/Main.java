package com.optlab;

import com.optlab.model.Doctor;
import com.optlab.model.ShiftCalendar;
import com.optlab.optimization.AntColonySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //Get month
        System.out.println("Please enter the month:");
        int month = in.nextInt();
        System.out.println("You entered: "+month);

        ShiftCalendar shiftCalendar = new ShiftCalendar(month);
        shiftCalendar.printCalendar();

        //Get num of doctors
        System.out.println("Please enter the num of doctors:");
        int numOfDoctors = in.nextInt();
        System.out.println("You entered: "+numOfDoctors);

        //Create Doctors
        List<Doctor> doctorList = new ArrayList<>();
        for (int i=0; i<=numOfDoctors; i++)
            doctorList.add(new Doctor());

        //CONSTRAINTS

        //CONSTRAINTS OF HOSPITAL


        //CONSTRAINT OF DOCTORS


        //WISHES
        //WISH OF DOCTORS

        AntColonySystem antColonySystem = new AntColonySystem();
        antColonySystem.solve();

    }
}
