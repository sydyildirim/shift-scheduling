package com.optlab.model;

public enum Criteria {

    //Doctors
    minShiftNumPerMonth,
    maxShiftNumPerMonth,
    minShiftNumPerWeekend,
    maxShiftNumPerWeekend,
    dayThatCannotWork,
    dayThatOff,

    //Hospital
    minNumDoctorPerShift,
    maxNumDoctorPerShift,

    //
    dayThatWantedToWork,
    shiftThat2DayInARow,
    shiftThatInWeekday,
    shiftThatInWeekend,
    shiftThatInPublicHoliday


}
