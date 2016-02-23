public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    static protected class Node<E> {
        E elt;
        Node<E> prev, next;

        public Node(E e, Node<E> l, Node<E> r) {
            elt = e;
            prev = l;
            next = r;
        }
    }

    private int size;
    private Node first = null;

    public SortedLinkedListSet() {
        size = 0;
    }



    @Override
    public int size() {
        return size;
    }

    /**
     * Adds a new node to the list using an recursive add-method
     * @param x the value of the new node
     * @return  returns true if the new node is added to the list, returns false if the value fails to be added to the list
     */
    @Override
    public boolean add(E x) {
        Node node;
        if (first == null) {
            node = new Node(x, null, null);
            first = node;
            size++;
            return true;
        }

        return addRec(first, x);
    }

    /**
     * The recursive add-method. By comparing the elt of the nodes, it determines the position of the new node
     *
     * @param node the node the method starts to compare with, in this case the root
     * @param e the value of the new node
     * @return returns true if the new node is added to the list, returns false if the value fails to be added to the list
     */
    private boolean addRec(Node<E> node, E e){

        int comp = e.compareTo(node.elt);

        if(comp == 0){
            return false;

        }else if(comp < 0){
            Node n;
            if(node.prev == null){
                n = new Node(e, null, node);
                first = n;

            }else{
                n = new Node(e, node.prev, node);
                node.prev.next = n;
            }
            node.prev = n;
            size++;
            return true;
        }

        if(node.next == null){
            Node n = new Node(e, node, null);
            node.next = n;
            size++;
            return true;
        }
        return addRec(node.next, e);

    }

    /**
     * Removes a node from the list by calling the recursive remove-function
     * @param x the data-value of a node that is going to be removed
     * @return Returns true if the node is removed from the list, returns false if it fails to remove it
     */
    @Override
    public boolean remove(E x) {

        return removeRec(first, x);
    }


    /**
     * The recursive remove-method. By finding the node that contains x as its elt, the method knows which node that is going to be removed
     * @param node the node the method starts to compare with, in this case the root
     * @param x the value of the node that is going to be removed
     * @return returns true if the node is removed, returns false if it fails to remove the node
     */
    private boolean removeRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);

        if(comp == 0){
            if(node.prev == null){
                first = node.next;
            }
            if(node.next != null) node.next.prev = node.prev;

            if(node.prev != null) node.prev.next = node.next;

            size--;
            return true;
        }else if(comp > 0){
            removeRec(node.next, x);
        }
        return false;
    }

    /**
     * Finds the node containing an elt as x using an recursive contain-method
     * @param x the value of the target node
     * @return returns true if list contains a node with that elt, returns false if there is not
     */
    @Override
    public boolean contains(E x) {
        return containsRec(first, x);
    }

    /**
     * The recursive contain-method. Finds the target node by comparing the value of each node
     * @param node the node the method starts to compare with, in this case the root
     * @param x the value of the target node
     * @return returns true if the node is found, returns false if it fails
     */
    private boolean containsRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);

        if(comp == 0){
            return true;

        } else if(comp > 0){
            containsRec(node.next, x);
        }

        return false;
    }
}
