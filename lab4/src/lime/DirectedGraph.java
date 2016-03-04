package lime;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.transformation.SortedList;
import lime.givna_filer.*;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class DirectedGraph<E extends Edge> {


	/**
	 * Comparator class that is used in
     * the priority queue to sort the elements.
     *
     * Sorting depending on the edge's weight
	 */
	private class CompKruskalEdge implements Comparator<E>{

		@Override
		public int compare(E o1, E o2) {
			if(o1.getWeight() > o2.getWeight()) return 1;

			if(o1.getWeight() < o2.getWeight()) return -1;

			return 0;
		}
	}


	//List<E> sq = new ArrayList<>();
	PriorityQueue<E> pq;

	Map<Integer, Set<E>> nodeMap;

	public DirectedGraph(int noOfNodes) {

		//vertexes = noOfNodes;
		nodeMap = new HashMap<>();
		pq = new PriorityQueue(noOfNodes, new CompKruskalEdge());

		for(int i = 0; i < noOfNodes; i++){
			nodeMap.put(i, new HashSet<E>());
		}



	}

    /**
     * Adds an edge to the graph.
     * @param e
     */
	public void addEdge(E e) {
		Set<E> set = nodeMap.get(e.from);

		set.add(e);

		nodeMap.put(e.from, set);

        // Adding to priority queue right away
		pq.add(e);

	}

    private class DijkstraCompPath implements Comparable {



        private int node;
        private Set<E> path;
        private double weight;

        private DijkstraCompPath(int node){
            this.node = node;
            this.path = new HashSet<>();
            weight = 0;
        }

        private DijkstraCompPath(int node, Set<E> set){
            this.node = node;
            this.path = new HashSet<>();
            this.path.addAll(set);

            setWeight();
        }


        private void setWeight(){
            Iterator<E> iter = this.path.iterator();
            double sum = 0;

            while(iter.hasNext()){
                sum += iter.next().getWeight();
            }

            weight = sum;
        }

        private void addEdge(E e){
            path.add(e);
            weight += e.getWeight();
        }

        private void addPath(Set<E> set){
            this.path.addAll(set);
            setWeight();
        }

        @Override
        public int compareTo(Object o) {

            DijkstraCompPath o1 = (DijkstraCompPath)o;

            if(this.weight > o1.weight) return 1;
            if(this.weight < o1.weight) return -1;

            return 0;
        }
    }

	public Iterator<E> shortestPath(int from, int to) {

        PriorityQueue<DijkstraCompPath> pq = new PriorityQueue<>();

        pq.add(new DijkstraCompPath(from));


        boolean [] visited = new boolean[nodeMap.size()];

        for (boolean b: visited){
            b = false;
        }

        while(!pq.isEmpty()){

            DijkstraCompPath d = pq.poll();

            if(!visited[d.node]){
                if(d.node == to){
                    return d.path.iterator();
                }else{
                    visited[d.node] = true;

                    Iterator<E> iter = nodeMap.get(d.node).iterator();

                    while (iter.hasNext()){
                        E e = iter.next();
                        if(! visited[e.to]){
                            DijkstraCompPath dc = new DijkstraCompPath(e.to, d.path);
                            dc.addEdge(e);
                            pq.add(dc);
                        }

                    }
                }

            }

    }
        return null;

        /*Set<Integer> s = new HashSet<>();
        s.add(from);

        Set<Integer> sMv = new HashSet<>();

        int [] p = new int [nodeMap.size()];
        double [] d = new double [nodeMap.size()];

        Map<Integer, Set<E>> thePaths = new HashMap<>();

        for(int i = 0; i < nodeMap.size(); i++){
            if(i != from ){
                sMv.add(i);
            }
            thePaths.put(i, new HashSet<E>());
        }


        for(int v: sMv){

            p[v] = from;

            E e = getEdge(from, v);
            if(e != null){
                Set<E> set = new HashSet<>();
                d[v] = e.getWeight();

                set.add(e);
                thePaths.put(v, set);
            }else{
                d[v] = Double.MAX_VALUE;
            }

        }


        while(!sMv.isEmpty()){

            Double smallest = null;
            Integer minPos = null;
            for(int u: sMv){

                if(minPos == null || d[u] < smallest){
                    smallest = d[u];
                    minPos = u;
                }
            }

            s.add(minPos);
            sMv.remove(minPos);

            if(minPos == to){
                return thePaths.get(to).iterator();
            }
            for(int v: sMv){
                E e = getEdge(minPos, v);

                if(e != null){

                    if((d[minPos] + e.getWeight()) < d[v]){
                        Set<E> set = thePaths.get(minPos);
                        Set<E> set1 = thePaths.get(v);
                        set1.addAll(set);
                        set1.add(e);
                        thePaths.put(v, set1);
                        d[v] = d[minPos] + e.getWeight();
                        p[v] = minPos;
                    }

                }

            }

        }

		return thePaths.get(to).iterator();
		*/

	}


    private E getEdge(int from, int to){

        Set <E> set = nodeMap.get(from);

        Iterator<E> iter = set.iterator();

        while(iter.hasNext()){
            E e = iter .next();
            if(e.from == from && e.to == to){
                return e;
            }
        }
        return null;
    }

    /**
     * Checks if there is a cycle in the graph.
     * A help method to the MST - function
     *
     * @param set the set that you want to check if it contains the from and to nodes
     * @param from which node the edge goes from
     * @param to which node the edge goes to
     * @return true if cycle, else false
     */
	private boolean checkCykel(Set<E> set, int from, int to){

		Iterator<E> iter = set.iterator();
		Set<Integer> conset = new HashSet<Integer>();

        // Adds all nodes to a new set
		while(iter.hasNext()){
			E e = iter.next();
			conset.add(e.from);
			conset.add(e.to);

		}
        // Check if the set contains the nodes
		return conset.contains(from) && conset.contains(to);
	}



	int nbrofedges = 0;

    /**
     * Method used to calculate MST. Used in ShortRoute
     *
     * @return an iterator containing the edges that creates the MST
     */
	public Iterator<E> minimumSpanningTree() {

        // Creates a new empty graph and fill it with all the nodes
		Map<Integer,Set<E>> cc = new HashMap<>();

		for (int i = 0; i < nodeMap.size(); i++){
			cc.put(i, new HashSet<E>());
		}

		while(!pq.isEmpty() && nbrofedges < nodeMap.size() ){

            // get the head-edge from the priority queue
			E e = pq.poll();

            // contains the merged set with edges from both from and to nodes
			Set<E> mergedSet;

            // contains the nodes that need to redirect thier set-pointers
			Set<E> nodes;

			Iterator<E> iter = null;

            if (cc.get(e.from).size() >= cc.get(e.to).size()) {
                mergedSet = merge(cc.get(e.from), cc.get(e.to), e);
                nodes = cc.get(e.to);
            } else {
                mergedSet = merge(cc.get(e.to), cc.get(e.from), e);
                nodes = cc.get(e.from);
            }

            iter = nodes.iterator();

            // redirect all the set-pointers for the nodes who were in the smallest set
            while (iter.hasNext()){
                E node = iter.next();
                cc.put(node.from, mergedSet);
                cc.put(node.to, mergedSet);
            }

            // make the to and from node point at the same set
            cc.put(e.from, mergedSet);
            cc.put(e.to, mergedSet);



			}

        // return the iterator. (All nodes are pointing at the same set)
		return cc.get(0).iterator();
	}

    /**
     * Merge the to sets together if there is no cycle
     * @param fromSet the set where you want to put all the edges from toSet (biggest set)
     * @param toSet the set you want to move all the edges from (smallest set)
     * @param e the new node
     * @return the new merged set
     */
	private Set<E> merge(Set<E> fromSet, Set<E> toSet, E e){

        // checks if cycle
		boolean isCycel = checkCykel(fromSet, e.from, e.to);

        // if there is not a cycle, merge and add the new node.
        // Else the nodes already are in the same list.
		if(!isCycel) {
			Iterator<E> iter = toSet.iterator();

			while (iter.hasNext()) {
				fromSet.add(iter.next());
			}

			fromSet.add(e);
			nbrofedges++;
		}

		return fromSet;
	}


	public static void main(String args[]){

		DirectedGraph dg = new DirectedGraph(133);
		NodeTable<BusStop> noderna  = new NodeTable<BusStop>();
		/*
		dg.addEdge(new BusEdge(0, 1, 3, ""));
		//dg.addEdge(new BusEdge(1, 0, 6, ""));

		dg.addEdge(new BusEdge(1, 2, 8, ""));
		//dg.addEdge(new BusEdge(2, 1, 5, ""));

		dg.addEdge(new BusEdge(2, 3, 4, ""));
		//dg.addEdge(new BusEdge(3, 2, 5, ""));

		dg.addEdge(new BusEdge(3, 0, 5, ""));
		//dg.addEdge(new BusEdge(0, 3, 5, ""));

		dg.addEdge(new BusEdge(0, 2, 1, ""));
		//dg.addEdge(new BusEdge(2, 0, 1, ""));

		dg.addEdge(new BusEdge(1, 3, 2, ""));
		//dg.addEdge(new BusEdge(4, 1, 3, ""));
		*/


		File f2 = new File(DirectedGraph.class.getResource("stops.noBOM.txt").getFile());


		Scanner indata = null;
		try {
			indata = new Scanner(f2, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (indata.hasNext()) {
			String hpl = indata.next().trim();
			int xco = indata.nextInt();
			int yco = indata.nextInt();
			noderna.add(new BusStop(hpl, xco, yco));

		}
		indata.close();

		File f1 = new File(ShortRoute.class.getResource("lines.noBOM.txt").getFile());
		try {
			indata =  new Scanner(f1, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		while ( indata.hasNext() ) {
			String lineNo = indata.next();
			int    antal  = indata.nextInt() -1;
			int    from   = noderna.find( indata.next() ).getNodeNo();
			// hur rita flera linjer mellan 2 noder?
			// enkel inc fungerar inte
			// färgen kunde vara "äkta" dvs linjefärg
			Color color = new Color((float)Math.random(),
					(float)Math.random(),
					(float)Math.random());
			for (int i = 0; i < antal; i++ ) {
				int to = noderna.find( indata.next() ).getNodeNo();
				BusEdge be = new BusEdge(from, to, indata.nextInt(), lineNo );
				System.out.println(be);
				dg.addEdge(be);

				from = to;
			}
		}
		indata.close();

		//System.out.println("size" +dg.sq.size());

		System.out.println("Done init edges");
		/*
		dg.addEdge(new BusEdge(0, 1, 2, ""));
		dg.addEdge(new BusEdge(1, 4, 2, ""));
		dg.addEdge(new BusEdge(4, 5, 3, ""));
		dg.addEdge(new BusEdge(5, 6, 4, ""));
		dg.addEdge(new BusEdge(6, 7, 1, ""));
		dg.addEdge(new BusEdge(1, 3, 5, ""));
		dg.addEdge(new BusEdge(3, 2, 8, ""));
		dg.addEdge(new BusEdge(2, 8, 2, ""));
		dg.addEdge(new BusEdge(0, 2, 7, ""));


		*/

		Iterator<Edge> iter = dg.minimumSpanningTree();

		while (iter.hasNext()){

			System.out.println(iter.next());

		}

		/*
		Map<Integer, Set<Edge>> map = new HashMap<>();

		map.put(1, new HashSet<>());

		map.put(2, new HashSet<>());

		map.put(3, new HashSet<>());

		Set<Edge> set1 = map.get(1);

		set1.add(new BusEdge(1,2,2,""));

		map.put(1, set1);
		map.put(2, set1);

		Set<Edge> set2 = map.get(1);

		set2.add(new BusEdge(2,1,5,""));

		map.put(1,set2);

		Set<Edge> set3 = map.get(3);

		set3.add(new BusEdge(2,3,5,""));

		Iterator<Edge> iter = set1.iterator();

		while(iter.hasNext()){
			set3.add(iter.next());
		}

		map.put(3,set3);
		map.put(1, set3);
		*/
	}

}
  
