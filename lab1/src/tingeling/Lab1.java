package tingeling;

public class Lab1 {

    public static double binarySqrt(double sqr, double eps) {
        if (sqr > 1) {
            return help(sqr, eps, 1, sqr);
        } else if (sqr == 1) {
            return 1;
        }
        return -1;
    }

    public static double help(double sqr, double eps, double low, double high) {
        double midpoint = ((high + low) / 2);
        double lowmidpoint = midpoint - eps;
        double highmidpoint = midpoint + eps;
        double sqrmidpoint = midpoint * midpoint;

        if (sqr <= highmidpoint * highmidpoint && sqr >= lowmidpoint * lowmidpoint) {
            return midpoint;
        } else {
            if (sqrmidpoint > (sqr + eps)) {
                return help(sqr, eps, low, midpoint);
            } else {
                return help(sqr, eps, midpoint, high);
            }
        }
    }


    public static void main(String[] args) {
        double result = binarySqrt(2, 10e-6);

        System.out.println("Sqrt " + result);
        System.out.println("Math.sqrt " + Math.sqrt(2));
    }
}
