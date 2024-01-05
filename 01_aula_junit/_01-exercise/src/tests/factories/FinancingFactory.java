package tests.factories;

import entities.Financing;

public class FinancingFactory {

    public static Financing createFinancing(){
        return new Financing(100000.0, 2000.0, 80);
    }

    public static Financing createEmptyFinancing(double totalAmount, double income, int months){
        return new Financing(totalAmount, income, months);
    }

}
