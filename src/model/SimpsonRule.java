package model;

import java.util.ArrayList;

public class SimpsonRule {
    private int num_of_formula;
    private double limitFrom;
    private double limitTo;
    private double precision;
    //count of steps
    private int n;
    //step offset
    private double h;
    private ArrayList<Double> xi;
    private ArrayList<Double> yi;
    private double result;
    private double result_2;

    public SimpsonRule(int num_of_formula, double limitFrom, double limitTo, double precision) {
        this.num_of_formula = num_of_formula;
        this.limitFrom = limitFrom;
        this.limitTo = limitTo;
        this.precision = precision;
        establishCorrectNandH();
        generateXi();
        generateYi();
        result = getResultOfIntegration(1);
        result_2 = getResultOfIntegration(2);
        if(RungeRule(result, result_2))
            System.out.println("You counted everything correctly! Answer is " + result);
        else
            System.out.println("Something went wrong..");
    }


    public boolean RungeRule (double Ih, double I2h) {
        return (Math.abs(Ih-I2h)/15 <= precision);
    }
    /**
     * @param step_multiplier - 1 or 2 - to check with RungeRule
     */
    private double getResultOfIntegration(int step_multiplier) {
        double res = yi.get(0) + yi.get(n);
        double odd = 0;
        double even = 0;
        for (int i = 1*step_multiplier; i < n; i += 2*step_multiplier) {
            odd += yi.get(i);
        }
        for (int i = 2*step_multiplier; i < n; i += 2*step_multiplier) {
            even += yi.get(i);
        }
        res += odd*4;
        res += even*2;
        res *= step_multiplier*h/3;
        return res;
    }
    private void generateYi() {
        for (int i = 0; i < n+1; i++) {
            //TODO list all of formulas
            switch (num_of_formula) {
                case 1:
                    yi.add(formula_1(xi.get(i)));
                    break;
            }
        }
    }
    private void generateXi(){
        double currentXi = limitFrom;
        xi.add(limitFrom);
        for (int i = 0; i < n-1; i++) {
            xi.add(currentXi+h);
        }
        xi.add(limitTo);
    }
    private void establishCorrectNandH() {
        n = getCorrectN();
        h = getCorrectH();
    }
    private double getCorrectH () {
        return  ((limitTo-limitFrom)/getCorrectN());
    }
    private int getCorrectN () {
        int multiplier = (int)(getN_UsingMaxH()/4);
        if (getN_UsingMaxH()%4 != 0)
            ++multiplier;
        return multiplier*4;
    }
    private double getN_UsingMaxH() {
        return ((limitTo-limitFrom)/getMaxOfH());
    }
    private double getMaxOfH(){
        return Math.pow(precision, 0.25);
    }

    public int getFormula() {
        return num_of_formula;
    }
    public double getLimitFrom() {
        return limitFrom;
    }
    public double getLimitTo() {
        return limitTo;
    }
    public double getPrecision() {
        return precision;
    }

    private double formula_1(double x) {
        //TODO delete stub and put formula
        return x;
    }
}
