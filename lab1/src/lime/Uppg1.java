package lime;

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
     * @param eps the the margin of error.
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
        System.out.println("--------------- Test 1 ------------");
        System.out.println("Check values x < 0, should return NaN");

        double sqrtR = mySqrtRecurse(-1, 10e-6);
        System.out.println("Using mySqrt");


        double sqrtL = mySqrtLoop(-1, 10e-6);
        System.out.println("SqrtR: " + sqrtR);
        System.out.println("SqrtL: " + sqrtL);

    }
}
