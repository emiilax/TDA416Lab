package lime;

import java.util.DoubleSummaryStatistics;

/**
 * Created by emilaxelsson on 20/01/16.
 */
public class Uppg1 {

    /**
     * Checks if the input values, (x, eps) are accepted values. Uses method helpRecursive to calculate the value
     * @param x the number you want to know the square root of. (Must be positive and larger or equal than one)
     * @param eps the margin of error. (must be larger than one)
     * @return the square root of x or -1 if x or eps are not accepted
     */
    public static double mySqrtRecurse(double x, double eps){
        if(x > 1){
            return helpRecursive(x, eps, 1, x);
        } else if(x <= 1 && x >= 0) {
            return helpRecursive(x, eps, x, 1);
        }
        // else x < 0
        return Double.NaN;
    }

    /**
     * Calculates the square root with "eps" accuracy. This is a recursive method
     * which calls it self until the answer is within the sqr(+-) eps area.
     * @param sqr the number you want to know the square root of.
     * @param eps the margin of error.
     * @param low smaller than correct value
     * @param high bigger than correct answer
     * @return
     */
    private static double helpRecursive(double sqr , double eps, double low, double high){
        double midValue = ((high+low)/2);
        //System.out.println("midValue: " + midValue);

        double midValueSquared = Math.pow(midValue , 2);
        //System.out.println("squared: " + midValueSquared );

        if(midValueSquared > sqr + eps) {
            // we now know it is lower than midValue, new highest value
            return helpRecursive(sqr, eps, low, midValue);
        }else if(midValueSquared < sqr - eps){
            // we now know it is higher than midvalue, new lowest value
            return helpRecursive(sqr, eps, midValue, high);
        }

        // between sqr-eps and sqr+eps, return value
        return midValue;

    }

    /**
     * Calculates the square root with foor-loop. It calculates the square root with "eps" accuracy.
     * @param x the number you want to find the square root of
     * @param epsilon the margin of error
     * @return it returns the square root of x while x>=0. If x is negative, it returns Double.NaN
     */

    public static double mySqrtLoop(double x, double epsilon){

        double max;
        double min;

        if(x > 1){
            max = x;
            min = 1;
        } else if(x <= 1 && x >= 0) {
            max = 1;
            min = x;
        } else {
            return Double.NaN;
        }
        double midValue = ((max+min)/2);
        double midValueSquared = Math.pow(midValue , 2);

        while (midValueSquared > x + epsilon || midValueSquared < x - epsilon){

            if(midValueSquared > x + epsilon){
                max = midValue;
            }else if(midValueSquared < x - epsilon){
                min = midValue;
            }
            midValue = ((max+min)/2);
            midValueSquared = Math.pow(midValue , 2);
        }

        return midValue;
    }



    public static void main(String [] args){

        // --------------------------- Test 1 -------------------------------------
        System.out.println("--------------- Test 1 ---------------");
        System.out.println("Check values x < 0, should print NaN:");
        System.out.println();
        System.out.println("Checks value x = -1");
        System.out.println();

        double sqrtR = mySqrtRecurse(-1, 10e-6);
        double sqrtL = mySqrtLoop(-1, 10e-6);

        if(Double.compare(Double.NaN, sqrtR) == 0) {
            System.out.println("Correct: Using mySqrtRecursive = " + sqrtR);
        }else{
            System.out.printf("Fail: mySqrtRecursive function failed");
        }

        if(Double.compare(Double.NaN, sqrtL) == 0){
            System.out.println("Correct: Using mySqrtLoop = " + sqrtL);
        } else{
            System.out.printf("Fail: mySqrtLoop function failed");
        }

        System.out.println();
        System.out.println();



        // --------------------------- Test 2 -------------------------------------
        System.out.println("--------------- Test 2 ---------------");
        System.out.println("Check values 0 =< x =< 1:");
        System.out.println();
        System.out.println("Checks value x = 0.5, value should be " + Math.sqrt(0.5) + " (+-) 10e-6 " );
        System.out.println();
        sqrtR = mySqrtRecurse(0.5, 10e-6);
        sqrtL = mySqrtLoop(0.5, 10e-6);

        if(sqrtR < Math.sqrt(0.5) + 10e-6 && sqrtR > Math.sqrt(0.5) - 10e-6){
            System.out.println("Correct: Using mySqrtRecursive = " + sqrtR);
        } else{
            System.out.printf("Fail: mySqrtRecursive function failed");
        }

        if(sqrtL < Math.sqrt(0.5) + 10e-6 && sqrtL > Math.sqrt(0.5) - 10e-6){
            System.out.println("Correct: Using mySqrtLoop = " + sqrtL);
        }else{
            System.out.printf("Fail: mySqrtLoop function failed");
        }
        System.out.println();
        System.out.println();

        // --------------------------- Test 3 -------------------------------------
        System.out.println("--------------- Test 3 ---------------");
        System.out.println("Check the limited values x = 0 and x = 1 :");
        System.out.println();
        System.out.println("Checks value x = 0, value should be 0"  );
        System.out.println();
        sqrtR = mySqrtRecurse(0, 10e-6);
        sqrtL = mySqrtLoop(0, 10e-6);

        if(sqrtR < 10e-6 && sqrtR > (-10e-6) ){
            System.out.println("Correct: Using mySqrtRecursive (x = 0) = " + sqrtR);
        } else{
            System.out.println("Fail: mySqrtRecursive function failed");
        }

        if(sqrtL < 10e-6 && sqrtL > (-10e-6) ){
            System.out.println("Correct: Using mySqrtLoop (x = 0) = " + sqrtL);
        }else{
            System.out.println("Fail: mySqrtLoop function failed");
        }

        System.out.println();
        System.out.println("Checks value x = 1, value should be 1"  );
        System.out.println();
        sqrtR = mySqrtRecurse(1, 10e-6);
        sqrtL = mySqrtLoop(1, 10e-6);

        if(sqrtR < 1 + 10e-6 && sqrtR > 1 - 10e-6){
            System.out.println("Correct: Using mySqrtRecursive (x = 0) = " + sqrtR);
        } else{
            System.out.printf("Fail: mySqrtRecursive function failed");
        }

        if(sqrtL < 1 + 10e-6 && sqrtL > 1 - 10e-6){
            System.out.println("Correct: Using mySqrtLoop (x = 0) = " + sqrtL);
        }else{
            System.out.printf("Fail: mySqrtLoop function failed");
        }
        System.out.println();
        System.out.println();

        // --------------------------- Test 4 -------------------------------------
        System.out.println("--------------- Test 4 ---------------");
        System.out.println("Check values x>1 and not a even number:");
        System.out.println();
        System.out.println("Checks value x = 3.78, value should be " + Math.sqrt(3.78) + " (+-) 10e-6 " );
        System.out.println();
        sqrtR = mySqrtRecurse(3.78, 10e-6);
        sqrtL = mySqrtLoop(3.78, 10e-6);

        if(sqrtR < Math.sqrt(3.78) + 10e-6 && sqrtR > Math.sqrt(3.78) - 10e-6){
            System.out.println("Correct: Using mySqrtRecursive (= " + sqrtR);
        } else{
            System.out.printf("Fail: mySqrtRecursive function failed");
        }

        if(sqrtL < Math.sqrt(3.78) + 10e-6 && sqrtL > Math.sqrt(3.78) - 10e-6){
            System.out.println("Correct: Using mySqrtLoop = " + sqrtL);
        }else{
            System.out.printf("Fail: mySqrtLoop function failed");
        }
        System.out.println();
        System.out.println();

        // --------------------------- Test 5 -------------------------------------
        System.out.println("--------------- Test 5 ---------------");
        System.out.println("Check values x>1 and a big number :");
        System.out.println();
        System.out.println("Checks value x = 123456789, value should be " + Math.sqrt(123456789) + " (+-) 10e-6 " );

        sqrtR = mySqrtRecurse(123456789, 10e-6);
        sqrtL = mySqrtLoop(123456789, 10e-6);

        if(sqrtR < Math.sqrt(123456789) + 10e-6 && sqrtR > Math.sqrt(123456789) - 10e-6){
            System.out.println("Correct: Using mySqrtRecursive = " + sqrtR);
        } else{
            System.out.printf("Fail: mySqrtRecursive function failed");
        }

        if(sqrtL < Math.sqrt(123456789) + 10e-6 && sqrtL > Math.sqrt(123456789) - 10e-6){
            System.out.println("Correct: Using mySqrtLoop = " + sqrtL);
        }else{
            System.out.printf("Fail: mySqrtLoop function failed");
        }
        System.out.println();



    }
}
