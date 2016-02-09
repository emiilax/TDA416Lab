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

    Node node = list.first;

    for(int i = 2; i < poly.length-2; i = i+2){

      double [] point = {poly[i], poly[i+1]};

      node = list.insertAfter(point, node);

      priorityQueue.add(node);

    }

    while((priorityQueue.size() + 2) > k){
      Node reNode = priorityQueue.remove();
      list.remove(reNode);


    }

    double [] newPoly = new double [(k * 2)];
    Node currnode = list.first;
    int i = 0;
    while (currnode != null){

      newPoly[i] = ((double [])currnode.elt)[0];
      newPoly[i+1] = ((double [])currnode.elt)[1];
      currnode = currnode.getNext();

      i = i + 2;

    }
    return newPoly;
  }
}
