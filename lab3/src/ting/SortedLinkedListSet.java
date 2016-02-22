package ting;

public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    static protected class Node<E> {
        E data;
        Node<E> prev, next;

        public Node(E e, Node<E> l, Node<E> r) {
            data = e;
            prev = l;
            next = r;
        }
    }

    protected Node first = null;
    private int size = 0;

    public SortedLinkedListSet() {
        size=0;
        first=null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {

        if (first == null) {
            size++;
            first = new Node(x, null, null);
            return true;
        } else {
            return addRecursive(first, x);
        }

    }

    private boolean addRecursive(Node<E> node, E e) {
        int comp = e.compareTo(node.data);
        if (comp == 0) {
            return false;
        } else if (comp < 0) {
            size++;
            Node newNode;
            if (node.prev == null) {
                newNode = new Node<E>(e, null, node);
                node.prev = newNode;
                first = newNode;
                return true;
            } else {
                newNode = new Node<E>(e, node.prev, node);
                node.prev.next = newNode;
                node.prev = newNode;
                return true;
            }

        } else {
            if (node.next == null) {
                Node newNode = new Node<E>(e, null, null);
                node.next = newNode;
                newNode.prev = node;
                size++;
                return true;
            } else {
                return addRecursive(node.next, e);
            }
        }
    }


    @Override
    public boolean remove(E x) {
        return removeRecurisve(first, x);
    }

    private boolean removeRecurisve(Node<E> node, E x) {
        int comp = x.compareTo(node.data);
        if (comp == 0) {
            if (node.prev == null) {
                node.next.prev = null;
            } else {
                node.prev.next = node.next;
            }
            if (node.next == null) {
                node.prev.next = null;
            } else {
                node.next.prev = node.prev;
            }
            size--;
            return true;
        } else {
            if (node.next != null) {
                return removeRecurisve(node.next, x);
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean contains(E x) {
        return containRecursive(first, x);
    }

    private boolean containRecursive(Node<E> node, E x) {
        if (node == null) {
            return false;
        } else {
            int comp = x.compareTo(node.data);
            if (comp == 0) {
                return true;
            }
            return containRecursive(node.next, x);

        }
    }

    public static void main(String[] args) {
        SortedLinkedListSet<Integer> list = new SortedLinkedListSet<>();

        list.add(new Integer(4));
        list.add(new Integer(3));
        list.add(new Integer(7));
        list.add(new Integer(1));

        Node n = list.first;
        while (n != null) {
            System.out.println(n.data);

            n = n.next;
        }
        System.out.println();

        System.out.println(list.contains(3));

        Node n1 = list.first;
        while (n1 != null) {
            System.out.println(n1.data);

            n1 = n1.next;
        }
        System.out.println();

    }
}