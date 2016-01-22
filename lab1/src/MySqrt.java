
public class MySqrt {

    /**
     * Calculates the square root with foor-loop and estimates the answer with epsilon accuracy.
     * @param x the number you want to find the square root of
     * @param epsilon the margin of error
     * @return it returns the square root of x while x>=0. If x is negative, it returns Double.NaN
     */

    public static double mySqrtLoop(double x, double epsilon){

        double max;
        double min;

        if(x <= 1 && x > 0) {
            max = 1;
            min = x;
        } else if(x < 0){
            return Double.NaN;
        } else if(x==0) {
            return 0;
        } else {
            max = x;
            min = 1;
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

    /**
     * A wrapper method that call helpRecursive in order to calculate the square root of x.
     * @param x       the number we are going to find a square of
     * @param epsilon the margin of error
     * @return returns the square root of x while x>=0. Returns Double NaN if x is negative.
     */
    public static double mySqrtRecurse(double x, double epsilon) {
        if (x > 1) {
            return helpRecursive(x, epsilon, 1, x);
        } else if (x <= 1 && x >= 0) {
            return helpRecursive(x, epsilon, x, 1);
        }
        return Double.NaN;
    }

    /**
     * The recursive method that calculates the square root of x.
     * @param x the number we are going to find square of
     * @param eps the margin of error
     * @param min the minimum number of the interval
     * @param max the maximum number of the interval
     * @return returns the square root of sqr
     */
    public static double helpRecursive(double x, double eps, double min, double max) {
        double midpoint = ((max + min) / 2);
        double lowmidpoint = midpoint - eps;
        double highmidpoint = midpoint + eps;
        double sqrmidpoint = midpoint * midpoint;

        //Returns midpoint if midpoint lies in the interval around x with "eps" accuracy
        if (highmidpoint * highmidpoint>=x && x >= lowmidpoint * lowmidpoint) {
            return midpoint;
        }

        if (sqrmidpoint > (x + eps)) {
            //Call this method again with midpont as the maximum number of the interval
            return helpRecursive(x, eps, min, midpoint);
        } else {
            //Call this method again with midpont as the minimum number of the interval
            return helpRecursive(x, eps, midpoint, max);
            }

    }



}
