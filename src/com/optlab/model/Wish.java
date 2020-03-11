package com.optlab.model;

public class Wish {

    private Criteria criteria;
    private double criteriaCoefficient;

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public double getCriteriaCoefficient() {
        return criteriaCoefficient;
    }

    public void setCriteriaCoefficient(double criteriaCoefficient) {
        this.criteriaCoefficient = criteriaCoefficient;
    }
}
