package ting;

public class Lab2a {

    public static double[] simplifyShape(double[] poly, int k) {
        double[] copy = poly;


        while ((copy.length / 2) > k) {
            double[] lessValue = {0, 0};

            for (int i = 2; i < copy.length - 2; i=i+2) {
                double[] L = {copy[i - 2], copy[i - 1]};
                double[] P = {copy[i], copy[i + 1]};
                double[] R = {copy[i + 2], copy[i + 3]};

                double l1 = Math.sqrt(Math.pow(P[0] - L[0], 2) + Math.pow(P[1] - L[1], 2));
                double l2 = Math.sqrt(Math.pow(R[0] - P[0], 2) + Math.pow(R[1] - P[1], 2));
                double l3 = Math.sqrt(Math.pow(R[0] - L[0], 2) + Math.pow(R[1] - R[1], 2));

                double value = Math.abs(l1 + l2 - l3);

                if (lessValue[0] > value || i==2) {
                    lessValue[0] = value;
                    lessValue[1] = i;
                }

        }
            copy=removeElement(copy,lessValue[1]);
    }

    return copy;
}

    public static double[] removeElement(double[] orgArray, double position) {
        double[] newArray = new double[orgArray.length - 2];

        for (int i = 0; i < newArray.length; i++) {
            if (i < position) {
                newArray[i] = orgArray[i];
            } else {
                newArray[i] = orgArray[i + 2];
            }
        }

        return simplifyShape(newArray, 2);
    }

    public static void main(String[] args) {
        double[] test = {2, 5, 6, 8, 9, 10, 11, 15, 3, 5, 6, 7};
        simplifyShape(test, 2);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }

    }

}

