package lime;

import java.util.Comparator;
import lime.DLList;

/**
 * Created by emilaxelsson on 01/02/16.
 */
public class ElementComparator implements Comparator<DLList.Node> {
    private double [] L;
    private double [] P;
    private double [] R;
    private double valueO1;
    private double valueO2;

    @Override
    public int compare(DLList.Node o1, DLList.Node o2) {

        L = (double []) o1.getPrev().elt;
        P = (double []) o1.elt;
        R = (double []) o1.getNext().elt;

        valueO1 = calculateValue(L, P, R);

        L = (double []) o2.getPrev().elt;
        P = (double []) o2.elt;
        R = (double []) o2.getNext().elt;

        valueO2 = calculateValue(L, P, R);

        if((valueO1 <  valueO2)) return -1;
        if((valueO1 == valueO2)) return 0;

        return 1;

    }

    private double calculateValue(double[] L, double [] P, double [] R){

        double l1 = Math.sqrt(Math.pow(L[0]-P[0], 2) + Math.pow(L[1]-P[1], 2));
        double l2 = Math.sqrt(Math.pow(P[0]-R[0], 2) + Math.pow(P[1]-R[1], 2));
        double l3 = Math.sqrt(Math.pow(L[0]-R[0], 2) + Math.pow(L[1]-R[1], 2));

        return Math.abs(l1+l2-l3);
    }
}
