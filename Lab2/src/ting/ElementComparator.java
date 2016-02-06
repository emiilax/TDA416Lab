package ting;

import ting.*;
import ting.DLList;

import java.util.Comparator;

/**
 * Created by MichelleTL on 02/02/16.
 */
public class ElementComparator implements Comparator<DLList.Node> {

    @Override
    public int compare(DLList.Node o1, DLList.Node o2) {

        double o1Value;
        double o2Value;

        o1Value = calculateValue(o1);
        o2Value = calculateValue(o2);

        if(o2Value>o1Value){
            return -1;
        } else if(o2Value==o1Value){
            return 0;
        } else{
            return 1;
        }
    }

    public double calculateValue(DLList.Node node){
        double[] L = (double[]) node.getPrev().elt;
        double[] P = (double[]) node.elt;
        double[] R = (double[]) node.getNext().elt;

        double l1 = Math.sqrt(Math.pow(L[0] - P[0], 2) + Math.pow(L[1] - P[1], 2));
        double l2 = Math.sqrt(Math.pow(P[0] - R[0], 2) + Math.pow(P[1] - R[1], 2));
        double l3 = Math.sqrt(Math.pow(L[0] - R[0], 2) + Math.pow(L[1] - R[1], 2));

        return Math.abs(l1+l2-l3);
    }
}
