
public class MySqrt {

    /**
     * Calculates the square root with while-loop and estimates the answer with epsilon accuracy.
     * @param x the number you want to find the square root of
     * @param epsilon the margin of error
     * @return it returns the square root of x while x>=0. If x is negative, it returns Double.NaN
     */

    public static double mySqrtLoop(double x, double epsilon){

        double max;
        double min;

        if(x <= 1 && x > 0) { // between 0 and one
            max = 1;
            min = x;
        } else if(x < 0){ // smaller than zero, return NaN
            return Double.NaN;
        } else if(x==0) {
            return 0;
        } else {
            max = x;
            min = 1;
        }

        double midValue = ((max+min)/2);
        double midValueSquared = Math.pow(midValue , 2);
        
        // loops until midvalueSquared is in between x + eps, x -eps
        while (midValueSquared > x + epsilon || midValueSquared < x - epsilon){

            if(midValueSquared > x + epsilon){ // midvalueSquared bigger? new max
                max = midValue;
            }else if(midValueSquared < x - epsilon) { // midvalueSquared lower? new min
                min = midValue;
            }
            midValue = ((max+min)/2);
            midValueSquared = Math.pow(midValue , 2);
        }

        return midValue;
    }

    /**
     * A wrapper method that call helpRecursive in order to calculate the square root of x.
     * @param x       the number we are going to find a square of
     * @param epsilon the margin of error
     * @return returns the square root of x while x>=0. Returns Double NaN if x is negative.
     */
    public static double mySqrtRecurse(double x, double epsilon) {
        if (x > 1) {
            return helpRecursive(x, epsilon, 1, x);
        } else if(x==0){
            return 0;
        }else if (x <= 1 && x >= 0) {
            return helpRecursive(x, epsilon, x, 1);
        }
        return Double.NaN;
    }

    /**
     * The recursive method that calculates the square root of x.
     * @param x the number we are going to find square of
     * @param epsilon the margin of error
     * @param min the minimum number of the interval
     * @param max the maximum number of the interval
     * @return returns the square root of sqr
     */
    public static double helpRecursive(double x, double epsilon, double min, double max) {
        double midpoint = ((max + min) / 2);
        double sqrmidpoint = midpoint * midpoint;

        if (sqrmidpoint > (x + epsilon)) {
            //Call this method again with midpont as the maximum number of the interval
            return helpRecursive(x, epsilon, min, midpoint);
        } else if(sqrmidpoint < (x -epsilon)){
            //Call this method again with midpont as the minimum number of the interval
            return helpRecursive(x, epsilon, midpoint, max);
        }

        //Returns midpoint if midpoint lies in the interval around x with "epsilon" accuracy
        return midpoint;

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
