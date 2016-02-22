package ting;
//
///**
// * Created by MichelleTL on 16/02/16.
// */
//public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
//
//    static protected class Node<E> {
//        E data;
//        Node<E> parent, left, right;
//
//        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
//            data = e;
//            parent=p;
//            left = l;
//            right = r;
//        }
//    }
//
//    protected Node root = null;
//    private int size = 0;
//
//    public SplayTreeSet(){
//        root=null;
//        size=0;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean add(E x) {
//        if(root==null){
//            Node newNode=new Node<E>(x,null,null,null);
//            root=newNode;
//            size++;
//        }
//        return addRecursive(root,x);
//    }
//
//    private boolean addRecursive(Node<E> node,E x){
//        int comp=x.compareTo(node.data);
//        if(comp==0){
//           return false;
//        }
//        if(comp<0){
//            if(node.left==null) {
//                Node newNode=new Node<E>(x,node,null,null);
//                node.left=newNode;
//                size++;
//              //  splayElement(node.parent.parent,node.parent);
//                return true;
//            }
//
//            return addRecursive(node.left, x);
//
//        } else{
//
//            if(node.right==null) {
//                Node newNode=new Node<E>(x,node,null,null);
//                node.right=newNode;
//                size++;
//               // splayElement(node.parent.parent,node.parent);
//                return true;
//            }
//            return addRecursive(node.right, x);
//
//        }//lagt till i slutet
//
//
//    }
//
//    private void splayElement(Node<E> parent,Node<E> node){
//        int comp=node.data.compareTo(parent.data);
//
//        boolean leftChild;
//        if(comp<0){
//            leftChild=true;
//        }else{
//            leftChild=false;
//        }
//
//        comp=parent.data.compareTo(parent.parent.data);
//        boolean leftParent;
//        if(comp<0){
//            leftParent=true;
//        }else{
//            leftParent=false;
//        }
//
//        if((leftChild && leftParent) && !(leftChild && leftParent)){
//            System.out.println("ZigZig: " + node.parent.data + "" +node.data);
//        }else if((leftChild && !leftParent) && !(!leftChild && leftParent)){
////            splayZigZag(leftChild, parent,node);
//            System.out.println("Zigzag: " + node.parent.data + "" +node.data);
//        }else{
////            splayZig(parent, node);
//            System.out.println("Zig: " + node.parent.data + "" +node.data);
//        }
//
//
//    }
//
//    private void splayZigZig(boolean rotateLeft, Node<E> parent,Node<E> node){
////        int comp=parent.parent.data.compareTo(parent.parent.parent.data);
////        if(rotateLeft){
////            if(parent.left!=null){
////                parent.parent.right=parent.left;
////            }
////            parent.left=parent.parent;
////            if(comp<0){
////
////            }
////
////        }else{
////            if(node.left!=null){
////                parent.right=node.left;
////            }
////            node.left=parent;
////            parent.parent.right=node;
////        }
//    }
//
//    private void splayZigZag(boolean leftPC, Node<E> parent,Node<E> node){
//
//    }
//
//    private void splayZig(Node<E> parent,Node<E> node){
//
//    }
//
//    @Override
//    public boolean remove(E x) {
//        return false;
//    }
//
//    @Override
//    public boolean contains(E x) {
//        return false;
//    }
//
//
//    //metod för felsökning. Skriver ut splay-träet
//    public static void reverseInOrder(Node h, int indent) {
//        if (h != null) {
//            indent++;
//            reverseInOrder(h.right, indent);
//
//            for (int i = 0; i < indent; i++) {
//                System.out.print("  ");
//            }
//            System.out.println(h.data);
//
//            reverseInOrder(h.left, indent);
//        }
//    }
//
//    public static void main(String[] args){
//        SplayTreeSet<Integer> spList=new SplayTreeSet<>();
//
////        spList.add(new Integer(11));
////
////        spList.add(new Integer(12));
////        spList.add(new Integer(1));
////
////        spList.add(new Integer(9));
////        spList.add(new Integer(0));
////
////        spList.add(new Integer(10));
//        spList.add(new Integer(3));
//
//        spList.add(new Integer(2));
//        spList.add(new Integer(5));
//
//        spList.add(new Integer(4));
//        spList.add(new Integer(7));
//
//        spList.add(new Integer(6));
//        spList.add(new Integer(8));
//
//        System.out.println("The root is " + spList.root.data+ ".");
//
//        reverseInOrder(spList.root, 0);
////        while (n != null) {
////            System.out.println(n.data);
////
////            n = n.left;
////        }
//        System.out.println();
//
//
//
//
//    }
//
//}

        import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

        import java.util.Random;

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
                Node<E> n = deleteMaxLeft(node.left);
                node.elt = n.elt;
            } else if(node.right != null){
                root = node.right;
                node.right.parent = null;
            }

            if(root==node && size==1){
                root=null;
            }
            size--;

            return true;

        } else if(comp<0){
            return removeRec(node.left, x);
        }

        return removeRec(node.right, x);

    }

    private Node<E> deleteMaxLeft(Node<E> node){

        if(node.right == null) {
            node.parent.left = node.left;
            node.parent = null;
            return node;
        }

        return deleteMaxLeft(node.right);

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

        if (node == root){
            //System.out.println("return");
            return;
        }

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
            //node.parent = node.parent.parent;
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
            //node.parent = node.parent.parent;
        }


        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;

        if(node.parent == null) root = node;


    }

    public static void main(String [] args){

        SplayTreeSet<Integer> sts = new SplayTreeSet<>();

        sts.add(103);
        sts.add(14);
        sts.add(19);
        sts.add(53);
        sts.add(40);
        sts.add(165);
        sts.add(155);
        /*
        sts.add(120);
        sts.add(81);
        sts.add(92);
        sts.add(68);
        sts.add(12);
        sts.add(44);
        sts.add(29);*/

        /*
        Random rand = new Random();

        for(int i = 0; i < 10 ; i++){
            sts.add(rand.nextInt(200));
        }*/

        /*System.out.println(sts.root.elt);
        System.out.println(sts.root.left.left.elt);
        System.out.println(sts.root.right.elt);

        System.out.println("Contains 7 " + sts.contains(7));
        System.out.println(sts.root.elt);
        System.out.println(sts.remove(7));
        System.out.println("Contains 11 " + sts.contains(5));*/
    }



}
