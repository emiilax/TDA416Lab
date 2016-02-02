package lime;

import java.util.Comparator;
import lime.DLList;

/**
 * Created by emilaxelsson on 01/02/16.
 */
public class ElementComparator implements Comparator<DLList.Node> {

    @Override
    public int compare(DLList.Node o1, DLList.Node o2) {

        double [] L = (double []) o1.getPrev().elt;

        return 0;
    }
}
