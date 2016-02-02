package ting;

public class Lab2a {

    /**
     * Functions that
     *
     * @param poly the very first array
     * @param k    number of points that the new array should contain
     * @return the new array with fewer elements
     */
    public static double[] simplifyShape(double[] poly, int k) {

        if(poly.length<=4 || k<2) return poly;

        double[] copy = poly;

        //array that keeps information about the point with less value
        double[] valueInfo = {0.00, 0.00};

        while (((copy.length) / 2) > k ) {

            //start the loop in the second point and end in the penultimate point
            for (int i = 2; i < copy.length - 3; i = i + 2) {

                double[] L = {copy[i - 2], copy[i - 1]};
                double[] P = {copy[i], copy[i + 1]};
                double[] R = {copy[i + 2], copy[i + 3]};

                double l1 = Math.sqrt(Math.pow(L[0] - P[0], 2) + Math.pow(L[1] - P[1], 2));
                double l2 = Math.sqrt(Math.pow(P[0] - R[0], 2) + Math.pow(P[1] - R[1], 2));
                double l3 = Math.sqrt(Math.pow(L[0] - R[0], 2) + Math.pow(L[1] - R[1], 2));

                double currentValue = Math.abs(l1 + l2 - l3);

                //check if currentValue is less than the value saved in valueInfo
                //or if it is the very first computed value
                if (valueInfo[0] > currentValue || i == 2) {
                    valueInfo[0] = currentValue;
                    valueInfo[1] = i;
                }

            }
            copy = removeElement(copy, valueInfo[1]);
        }

        return copy;
    }

    /**
     * Returns a new array with two elements deleted.
     *
     * @param orgArray the original array with values
     * @param position the position of the x-coordinate of the point
     * @return the new array
     */
    public static double[] removeElement(double[] orgArray, double position) {
        double[] newArray = new double[orgArray.length - 2];

        for (int i = 0; i < newArray.length; i++) {
            if (i < position) {
                newArray[i] = orgArray[i];
            } else {
                newArray[i] = orgArray[i + 2];
            }
        }

        return newArray;
    }

    public static void main(String[] args) {

        double[] poly = {2, 1, 3, 1, 4, 2, 4, 3, 4, 4, 5, 4, 6, 4, 7, 4, 8, 4, 9, 4, 10, 5, 10, 6, 10, 7, 9, 8, 8, 8, 7, 8, 6, 7, 5, 8, 4, 9, 3, 8, 3, 7, 2, 5, 1, 4, 2, 3, 2, 2};
        double[] copy = simplifyShape(poly, 6);

        for (int i = 0; i < poly.length; i++) {

            System.out.println("Poly: " + poly[i]);

        }
        System.out.println();

        for (int i = 0; i < copy.length; i++) {

            System.out.println("copy: " + copy[i]);


        }

    }
}

