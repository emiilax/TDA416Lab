import java.util.*;

public class DirectedGraph<E extends Edge> {

    //	int nbrofedges = 0;
    Map<Integer, Set<E>> nodeMap;

    public DirectedGraph(int noOfNodes) {

        nodeMap = new HashMap<>();

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
//		pq.add(e);

    }

    public Iterator<E> shortestPath(int from, int to) {

        PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>();

        pq.add(new CompDijkstraPath(from));


        boolean [] visited = new boolean[nodeMap.size()];

        for (boolean b: visited){
            b = false;
        }

        while(!pq.isEmpty()){

            CompDijkstraPath d = pq.poll();

            if(!visited[d.node]){
                if(d.node == to){
                    return d.path.iterator();
                }else{
                    visited[d.node] = true;

                    Iterator<E> iter = nodeMap.get(d.node).iterator();

                    while (iter.hasNext()){
                        E e = iter.next();
                        if(! visited[e.to]){
                            CompDijkstraPath dc = new CompDijkstraPath(e.to, d.path);
                            dc.addEdge(e);
                            pq.add(dc);
                        }

                    }
                }

            }

        }
        return null;

    }

    /**
     * Returns the edge from from and to
     * @param from which node the edge goes from
     * @param to which node the edge goes to
     * @return returns the edge
     */
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
     * Method used to calculate MST. Used in ShortRoute
     *
     * @return an iterator containing the edges that creates the MST
     */
    public Iterator<E> minimumSpanningTree() {
        PriorityQueue<E> pq = new PriorityQueue<>(nodeMap.size(), new CompKruskalEdge());
        Iterator<Set <E>> it=nodeMap.values().iterator();

        while(it.hasNext()){
            Set <E> edgeSet=it.next();
            pq.addAll(edgeSet);
        }

        // Creates a new empty graph and fill it with all the nodes
        Map<Integer,Set<E>> cc = new HashMap<>();

        for (int i = 0; i < nodeMap.size(); i++){
            cc.put(i, new HashSet<E>());
        }

        while(!pq.isEmpty() && cc.get(0).size() < nodeMap.size() ){

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
        boolean isCycel = (fromSet == toSet);

        // if there is not a cycle, merge and add the new node.
        // Else the nodes already are in the same list.
        if(!isCycel) {
            Iterator<E> iter = toSet.iterator();

            while (iter.hasNext()) {
                fromSet.add(iter.next());
            }

            fromSet.add(e);
//			nbrofedges++;
        }

        return fromSet;
    }

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

    /**
     * Comparator class that use Dijkstra Algorithm to find the shortest paths
     */
    private class CompDijkstraPath implements Comparable {

        private int node;
        private Set<E> path;
        private double weight;

        private CompDijkstraPath(int node){
            this.node = node;
            this.path = new HashSet<>();
            weight = 0;
        }

        private CompDijkstraPath(int node, Set<E> set){
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

        @Override
        public int compareTo(Object o) {

            CompDijkstraPath o1 = (CompDijkstraPath)o;

            if(this.weight > o1.weight) return 1;
            if(this.weight < o1.weight) return -1;

            return 0;
        }
    }


}

