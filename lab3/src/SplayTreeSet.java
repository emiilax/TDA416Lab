

public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    /**
     * The nodes that is in the Tree
     * @param <E>
     */
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

    /**
     * Adds an element to the tree and "splays" it to the top.
     * Using a recursive method to add
     * @param x
     * @return
     */
    @Override
    public boolean add(E x) {

        // if the tree is empty, just add a root
        if(root == null){
            root = new Node(x, null, null, null);
            size++;
            return true;
        }

        return addRec(root, x);
    }


    /**
     * Recursive method that checks were to put the new element
     * @param node, the node it compares with. Starts with root
     * @param x, the value you want to add
     * @return true if add succeded, else false
     */
    private boolean addRec(Node<E> node, E x){

        int comp = x.compareTo(node.elt);

        // if the element exists, do not add it
        if(comp == 0){
            return false;

        // if bigger, go right
        }else if(comp > 0){

            if(node.right == null){
                node.right = new Node(x, node, null, null);
                size++;
                if(size > 1) splayElement(node.right);

                return true;
            }

            return addRec(node.right, x);

        }else{ // if smaller, go left
            if(node.left == null){
                node.left = new Node(x, node, null, null);
                size++;
                if(size > 1) splayElement(node.left);

                return true;
            }

            return addRec(node.left, x);
        }


    }

    /**
     * Removes an object if it exist. Using recursive method
     * @param x, the element you want to remove
     * @return
     */
    @Override
    public boolean remove(E x) {

        return removeRec(root, x);

    }

    /**
     * Recursive method used to find which node you should remove.
     * @param node, the node you comparing the element with. starts with root
     * @param x, the element
     * @return true if rmove succeded, else false
     */
    private boolean removeRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);

        // if the node contains the element, remove it
        if(comp == 0){

            // splay the node to the top
            if(node != root){
                splayElement(node);
            }

            if(node.left != null){
                // remove root and replace it with its left child.
                Node<E> n = node.right;
                root = root.left;
                root.parent = null;

                // splay the left-right-most-child (biggest)
                splayElement(findMinLeft(node.left));

                root.right = n;
                if(root.right != null) root.right.parent = root;

            } else if(node.right != null){
                // if left child is null, just put the right child as root
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

    /**
     * Used to find the biggest node on the left side of the root
     * @param node,
     * @return the node with the biggest value
     */
    private Node<E> findMinLeft(Node<E> node){

        if(node.right == null){

            return node;

        }
        return findMinLeft(node.right);
    }


    /**
     * Checks if an element exists. Using a recursive method
     * @param x, the value that you want to check if it exist
     * @return true if exist, else false
     */
    @Override
    public boolean contains(E x) {

        return containsRec(root, x);

    }

    /**
     * Recursive method that chscks if elemet exists
     * @param node, the node you want to check the element against
     * @param x, the element you want to check if it exist
     * @return true if exists, else false
     */
    private boolean containsRec(Node<E> node, E x){

        if(node == null) return false;

        int comp = x.compareTo(node.elt);


        if( comp == 0 ){
            // if element exist, splay to to and return true
            splayElement(node);
            return true;
        }else if(comp < 0){
            return containsRec(node.left, x);
        }

        return  containsRec(node.right, x);
    }

    /**
     * Recursive method used to splay the element to the root.
     * @param node, the node that you want to splay
     */
    private void splayElement(Node<E> node) {
        // if it is at the root, return
        if (node == root) return;

        // just a zig-operation
        if(node.parent.parent == null){
            // checks whether its a left or right zig
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


    /**
     * Rotates a node to the right.
     * @param node, the node that you want to rotate
     */
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

    /**
     * Rotates a node to the left.
     *
     * @param node, the node that you want to rotate
     */
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
