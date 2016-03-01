package lime;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.transformation.SortedList;
import lime.givna_filer.BusEdge;
import lime.givna_filer.DrawGraph;
import lime.givna_filer.Edge;
import java.util.*;

public class DirectedGraph<E extends Edge> {


	private class EdgeComparator implements Comparator<E>{

		@Override
		public int compare(E o1, E o2) {
			if(o1.getWeight() > o2.getWeight()) return 1;
			if(o1.getWeight() < o2.getWeight()) return -1;

			return 0;
		}
	}


	List<E> sq = new ArrayList<>();

	Map<Integer, Set<E>> nodeMap;

	//int vertexes, edges;
	int edges;

	public DirectedGraph(int noOfNodes) {

		//vertexes = noOfNodes;
		edges = 0;
		nodeMap = new HashMap<>();



		for(int i = 0; i < noOfNodes; i++){
			nodeMap.put(i, new HashSet<E>());
		}

	}

	public void addEdge(E e) {
		Set<E> set = nodeMap.get(e.from);

		set.add(e);

		nodeMap.put(e.from, set);

		edges++;
		sq.add(e);

	}

	public Iterator<E> shortestPath(int from, int to) {






		return null;
	}


	private boolean checkCykel(Set<E> set, int from, int to){

		Iterator<E> iter = set.iterator();
		Set<Integer> conset = new HashSet<Integer>();
		

		while(iter.hasNext()){
			E e = iter.next();
			conset.add(e.from);
			conset.add(e.to);

		}
		return conset.contains(from) && conset.contains(to);
	}
		
	public Iterator<E> minimumSpanningTree() {

		int nbrofedges = 0;
		Collections.sort(sq, new EdgeComparator());
		Map<Integer,Set<E>> cc = new HashMap<>();

		for (int i = 0; i < nodeMap.size(); i++){
			cc.put(i, new HashSet<E>());
		}

		while(!sq.isEmpty() && nbrofedges < nodeMap.size() ){

			E e = sq.get(0);
			sq.remove(e);


			if(cc.get(e.from) != cc.get(e.to)) {

				if (cc.get(e.from).size() >= cc.get(e.to).size()) {

					Set<E> set1 = cc.get(e.from);
					Set<E> set2 = cc.get(e.to);

					if(checkCykel(set1, e.from, e.to)) break;

					Iterator<E> iter = set2.iterator();

					while (iter.hasNext()) {
						set1.add(iter.next());
					}

					set1.add(e);
					nbrofedges++;

					cc.put(e.from, set1);
					cc.put(e.to, set1);
				} else {
					Set<E> set1 = cc.get(e.from);
					Set<E> set2 = cc.get(e.to);

					if(checkCykel(set2, e.from, e.to)) break;

					Iterator<E> iter = set1.iterator();

					while (iter.hasNext()) {
						set2.add(iter.next());
					}

					set2.add(e);
					nbrofedges++;

					cc.put(e.from, set2);
					cc.put(e.to, set2);
				}

			}
		}

		return cc.get(0).iterator();
	}


	public static void main(String args[]){

		DirectedGraph dg = new DirectedGraph(6);

		dg.addEdge(new BusEdge(0, 1, 6, ""));
		//dg.addEdge(new BusEdge(1, 0, 6, ""));

		dg.addEdge(new BusEdge(1, 2, 5, ""));
		//dg.addEdge(new BusEdge(2, 1, 5, ""));

		dg.addEdge(new BusEdge(2, 3, 5, ""));
		//dg.addEdge(new BusEdge(3, 2, 5, ""));

		dg.addEdge(new BusEdge(3, 0, 5, ""));
		//dg.addEdge(new BusEdge(0, 3, 5, ""));

		dg.addEdge(new BusEdge(0, 2, 1, ""));
		//dg.addEdge(new BusEdge(2, 0, 1, ""));

		dg.addEdge(new BusEdge(1, 4, 3, ""));
		//dg.addEdge(new BusEdge(4, 1, 3, ""));

		dg.addEdge(new BusEdge(4, 5, 6, ""));
		//dg.addEdge(new BusEdge(5, 4, 6, ""));

		dg.addEdge(new BusEdge(4, 2, 6, ""));
		//dg.addEdge(new BusEdge(2, 4, 6, ""));

		dg.addEdge(new BusEdge(5, 2, 4, ""));
		//dg.addEdge(new BusEdge(2, 5, 4, ""));

		dg.addEdge(new BusEdge(5, 3, 2, ""));
		//dg.addEdge(new BusEdge(3, 5, 2, ""));

		System.out.println("Done init edges");

		Iterator<Edge> iter = dg.minimumSpanningTree();

		while (iter.hasNext()){

			System.out.println(iter.next());

		}


	}

}
  
