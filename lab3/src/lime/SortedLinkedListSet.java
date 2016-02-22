package lime;

/**
 * Created by emilaxelsson on 12/02/16.
 */
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

    @Override
    public boolean remove(E x) {

        return removeRec(first, x);
    }

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

    @Override
    public boolean contains(E x) {
        return containsRec(first, x);
    }

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



    public static void main(String [] args){

        SortedLinkedListSet<Integer> list = new SortedLinkedListSet<>();

        list.add(new Integer(4));
        list.add(new Integer(3));
        list.add(new Integer(7));
        list.add(new Integer(1));

        Node n = list.first;

        System.out.println("size " + list.size());
        while (n != null){
            System.out.println(n.elt);

            n = n.next;
        }

        n = list.first;
        while (n != null){

            System.out.println(n.elt);

            n = n.next;
        }




    }
}
