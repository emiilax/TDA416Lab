package lime;

import java.util.Comparator;
import java.util.PriorityQueue;
import lime.DLList.Node;


public class Lab2b {

  public static double[] simplifyShape(double[] poly, int k) {

    DLList<double[]> list= new DLList<>();

    Comparator<DLList.Node> comparator = new ElementComparator();
    PriorityQueue<DLList.Node> priorityQueue = new PriorityQueue<>(poly.length/2, comparator);

    double [] pointstart = {poly[0], poly[1]};
    double [] pointstop = {poly[poly.length-2], poly[poly.length-1]};

    list.addFirst(pointstart);
    list.addLast(pointstop);
    priorityQueue.add(list.first);
    priorityQueue.add(list.last);

    Node node = list.first;

    for(int i = 2; i < poly.length-2; i = i+2){

      double [] point = {poly[i], poly[i+1]};

      node = list.insertAfter(point, node);
      priorityQueue.add(node);
    }

    
    return null;
      // TODO
  }
}
