package lime;

/**
 * Created by emilaxelsson on 12/02/16.
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    static protected class Node<E> {
        E elt;
        Node<E> parent, left, right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            elt = e;
            parent = p;
            left = l;
            right = r;
        }
    }

    private int size;
    private Node<E> root;


    public SplayTreeSet(){
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {

        if(root == null){
            root = new Node(x, null, null, null);
            size++;
            return true;
        }

        return addRec(root, x);
    }


    private boolean addRec(Node<E> node, E x){

        int comp = x.compareTo(node.elt);

        if(comp == 0){
            return false;

        }else if(comp > 0){

            if(node.right == null){
                node.right = new Node(x, node, null, null);
                size++;
                if(size > 1) splayElement(node.right);

                return true;
            }

            return addRec(node.right, x);

        }else{
            if(node.left == null){
                node.left = new Node(x, node, null, null);
                size++;
                if(size > 1) splayElement(node.left);
                return true;
            }

            return addRec(node.left, x);
        }


    }

    @Override
    public boolean remove(E x) {
        return false;
    }

    @Override
    public boolean contains(E x) {
        return false;
    }



    private void splayElement(Node<E> node){

        if(node.equals(root)) return;

        if(node.parent.parent == null){
            if (node.parent.right == node) {
                rotateLeft(node);
            } else if (node.parent.left == node) {
                rotateRight(node);
            }
        }else if((node.parent.right == node) && (node.parent.parent.right == node.parent)){
            //zig-zig to left
            rotateLeft(node.parent);
            rotateLeft(node);
        } else if((node.parent.left == node)  && (node.parent.parent.left == node.parent)){
            //zig-zig to right
            rotateRight(node.parent);
            rotateRight(node);
        } else if((node.parent.left == node)  && (node.parent.parent.right == node.parent)){
            //zig-zag right-left
            rotateRight(node);
            rotateLeft(node);
        } else if((node.parent.right == node) && (node.parent.parent.left == node.parent)){
            //zig-zag left-right
            rotateLeft(node);
            rotateRight(node);
        }

        splayElement(node);

    }

    private void rotateRight(Node<E> node) {
        node.parent.left = node.right;
        node.right = node.parent;

        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;

        if(node.parent == null) root = node;

    }

    private void rotateLeft(Node<E> node) {
        node.parent.right = node.left;
        node.left = node.parent;

        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;

        if(node.parent == null) root = node;


    }

    static SplayTreeSet<Integer> sts;
    public static void main(String [] args){

        sts = new SplayTreeSet<>();

        sts.add(5);
        sts.add(3);
        sts.add(7);
        sts.add(11);
        sts.add(10);

        System.out.println(sts.root.elt);
        System.out.println(sts.root.left.left.elt);
        System.out.println(sts.root.right.elt);
    }



}
