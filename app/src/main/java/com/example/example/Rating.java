package com.example.example;

public class Rating {

    private double q1;
    private double q2;
    private double q3;
    private double q4;
    private double q5;

    public Rating(double qu1,double qu2,double qu3,double qu4,double qu5){
        q1=qu1;
        q2=qu2;
        q3=qu3;
        q4=qu4;
        q5=qu5;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }

    public void setQ2(double q2) {
        this.q2 = q2;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

    public void setQ4(double q4) {
        this.q4 = q4;
    }

    public void setQ5(double q5) {
        this.q5 = q5;
    }

    public double getQ1() {
        return q1;
    }

    public double getQ2() {
        return q2;
    }

    public double getQ3() {
        return q3;
    }

    public double getQ4() {
        return q4;
    }

    public double getQ5() {
        return q5;
    }

}
