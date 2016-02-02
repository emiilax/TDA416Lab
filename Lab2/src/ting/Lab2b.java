package ting;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Lab2b {

    public static double[] simplifyShape(double[] poly, int k) {

        Comparator comp=new ElementComparator();
        PriorityQueue<DLList.Node> prq= new PriorityQueue<>((poly.length/2, comp));


    }

    public static void main(String[] main) {

    }
}
