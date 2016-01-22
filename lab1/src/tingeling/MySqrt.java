package tingeling;

public class MySqrt {


   public static double mySqrtLoop(double x, double epsilon) { //not fully completed yet
        double min = 1;
        double max = x;
        double tmp = (x + 1) / 2;

        if (x > 1) {
            while (!(tmp * tmp >= (x - epsilon) && (tmp * tmp) <= (x + epsilon))) {
                tmp=(max+min)/2;
               if(tmp*tmp<(x-epsilon)){
                    min=tmp;
                }else{
                    max=tmp;
                }

            }
            return tmp;
        }  else if (x <= 1 && x >= 0) {
            return helpRecursive(x, epsilon, x, 1);
        }
        return Double.NaN;
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
     * @param sqr the number we are going to find square of
     * @param eps the margin of error
     * @param low the lower
     * @param high
     * @return
     */
    public static double helpRecursive(double sqr, double eps, double low, double high) {
        double midpoint = ((high + low) / 2);
        double lowmidpoint = midpoint - eps;
        double highmidpoint = midpoint + eps;
        double sqrmidpoint = midpoint * midpoint;

        if (sqr <= highmidpoint * highmidpoint && sqr >= lowmidpoint * lowmidpoint) {
            return midpoint;
        } else {
            if (sqrmidpoint > (sqr + eps)) {
                return helpRecursive(sqr, eps, low, midpoint);
            } else {
                return helpRecursive(sqr, eps, midpoint, high);
            }
        }
    }


    public static void main(String[] args) {
        double result = mySqrtRecurse(2, 10e-6);
        double loopresult = mySqrtLoop(2, 10e-6);
        System.out.println("Sqrt " + result);
        System.out.println("Loop " + loopresult);
        System.out.println("Math.sqrt " + Math.sqrt(2));
    }
}
