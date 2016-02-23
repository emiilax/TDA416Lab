/**
 * Created by MichelleTL on 23/02/16.
 */
public class SplayTreeSet {


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

        return removeRec(root, x);

    }


    private boolean removeRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);

        if(comp == 0){


            if(node != root){
                splayElement(node);
            }

            if(node.left != null){
                Node<E> n = node.right;
                root = root.left;
                root.parent = null;

                splayElement(findMinLeft(node.left));

                root.right = n;
                if(root.right != null) root.right.parent = root;

            } else if(node.right != null){
                Node<E> n = root.left;
                root = root.right;
                root.parent = null;
            }

            if(root == node && size == 1) root = null;
            size--;
            return true;


        } else if(comp<0){
            return removeRec(node.left, x);
        }

        return removeRec(node.right, x);

    }


    private Node<E> findMinLeft(Node<E> node){

        if(node.right == null){

            return node;

        }
        return findMinLeft(node.right);
    }


    @Override
    public boolean contains(E x) {

        return containsRec(root, x);

    }

    private boolean containsRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);

        if( comp == 0 ){
            splayElement(node);
            return true;
        }else if(comp < 0){
            return containsRec(node.left, x);
        }

        return  containsRec(node.right, x);
    }


    private void splayElement(Node<E> node) {

        if (node == root) return;

        if(node.parent.parent == null){
            if (node.parent.right == node) {
                rotateLeft(node);
            } else if (node.parent.left == node) {
                rotateRight(node);
            }
        }else if( (node.parent.right == node)  &&  (node.parent.parent.right == node.parent) ){
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

        if(node.right != null){
            node.right.parent = node.parent;
        }

        node.right = node.parent;

        if(node.parent.parent != null){

            if(node.parent.parent.right == node.parent){
                node.parent.parent.right = node;
            }else if(node.parent.parent.left == node.parent){
                node.parent.parent.left = node;
            }

        }

        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;

        if(node.parent == null) root = node;

    }

    private void rotateLeft(Node<E> node) {
        node.parent.right = node.left;
        if(node.left != null ) {
            node.left.parent = node.parent;
        }

        node.left = node.parent;


        if(node.parent.parent != null){

            if(node.parent.parent.right == node.parent){
                node.parent.parent.right = node;

            }else if(node.parent.parent.left == node.parent){
                node.parent.parent.left = node;
            }

        }


        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;

        if(node.parent == null) root = node;


    }


}
