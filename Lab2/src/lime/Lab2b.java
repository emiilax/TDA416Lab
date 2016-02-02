package lime;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Lab2b {
  public static double[] simplifyShape(double[] poly, int k) {

    DLList<double[]> list= new DLList<>();

    Comparator<DLList.Node> comparator = new ElementComparator();
    PriorityQueue<DLList.Node> priorityQueue = new PriorityQueue<>(poly.length, comparator);

    for(int i = 0; i < poly.length; i++){


    }

    double [] a = {1,2};
    list.addFirst(a);
    return null;
      // TODO
  }
}
