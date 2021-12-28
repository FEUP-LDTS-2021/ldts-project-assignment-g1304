package utils;

public class DoubleComparables {

    public static double compareDouble(double a, double b){
        return b-a;
    }

    public static boolean equalDouble(double a, double b){
        return Math.abs(compareDouble(a, b)) <= 0.010;
    }
}
