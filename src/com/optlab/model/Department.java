package com.optlab.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<DayOfWeek> operationDays;

    public Department(String departmentName){
        this.departmentName = departmentName;
        this.operationDays = new ArrayList();
    }

    public void setOperationDays(DayOfWeek day){
        operationDays.add(day);
    }
}
