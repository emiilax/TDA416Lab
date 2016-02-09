import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab2b {

    public static double[] simplifyShape(double[] poly, int k) {
        DLList<double[]> list = new DLList<>();
        Comparator<DLList.Node> comparator = new ElementComparator();
        PriorityQueue<DLList.Node> priorityQueue = new PriorityQueue<>(poly.length / 2, comparator);

        double[] pointFirst = {poly[0], poly[1]};
        double[] pointSecond = {poly[2], poly[3]};

        list.addFirst(pointFirst);
        DLList.Node node = list.insertAfter(pointSecond, list.getFirst());

        DLList.Node currentNode;
        for (int i = 4; i < poly.length; i = i + 2) {
            double[] point = {poly[i], poly[i + 1]};

            currentNode = list.insertAfter(point, node);
            priorityQueue.add(node);
            node = currentNode;
        }

        while (priorityQueue.size()+2 > k) {
            DLList.Node head = priorityQueue.remove(); //O(logn) faster
            list.remove(head);
            if (head.getPrev().getPrev() != null) {
                priorityQueue.remove(head.getPrev());
                priorityQueue.add(head.getPrev());
            }
            if (head.getNext().getNext() != null) {
                priorityQueue.remove(head.getNext());
                priorityQueue.add(head.getNext());
            }
        }

        DLList<double[]>.Node curr = list.getFirst();

        double[] polyArray = new double[k*2];
        int i = 0;
        while (curr != null) {
            polyArray[i] = curr.elt[0];
            polyArray[i + 1] = curr.elt[1];
            i = i + 2;
            curr = curr.getNext();

        }


        return polyArray;
    }
}
