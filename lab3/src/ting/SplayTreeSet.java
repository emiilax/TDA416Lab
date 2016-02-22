package ting;

/**
 * Created by MichelleTL on 16/02/16.
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    static protected class Node<E> {
        E data;
        Node<E> parent, left, right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            data = e;
            parent=p;
            left = l;
            right = r;
        }
    }

    protected Node root = null;
    private int size = 0;

    public SplayTreeSet(){
        root=null;
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {
        if(root==null){
            Node newNode=new Node<E>(x,null,null,null);
            root=newNode;
            size++;
        }
        return addRecursive(root,x);
    }

    private boolean addRecursive(Node<E> node,E x){
        int comp=x.compareTo(node.data);
        if(comp==0){
           return false;
        }
        if(comp<0){
            if(node.left==null) {
                Node newNode=new Node<E>(x,node,null,null);
                node.left=newNode;
                size++;
              //  splayElement(node.parent.parent,node.parent);
                return true;
            }

            return addRecursive(node.left, x);

        } else{

            if(node.right==null) {
                Node newNode=new Node<E>(x,node,null,null);
                node.right=newNode;
                size++;
               // splayElement(node.parent.parent,node.parent);
                return true;
            }
            return addRecursive(node.right, x);

        }//lagt till i slutet


    }

    private void splayElement(Node<E> parent,Node<E> node){
        int comp=node.data.compareTo(parent.data);

        boolean leftChild;
        if(comp<0){
            leftChild=true;
        }else{
            leftChild=false;
        }

        comp=parent.data.compareTo(parent.parent.data);
        boolean leftParent;
        if(comp<0){
            leftParent=true;
        }else{
            leftParent=false;
        }

        if((leftChild && leftParent) && !(leftChild && leftParent)){
            System.out.println("ZigZig: " + node.parent.data + "" +node.data);
        }else if((leftChild && !leftParent) && !(!leftChild && leftParent)){
//            splayZigZag(leftChild, parent,node);
            System.out.println("Zigzag: " + node.parent.data + "" +node.data);
        }else{
//            splayZig(parent, node);
            System.out.println("Zig: " + node.parent.data + "" +node.data);
        }


    }

    private void splayZigZig(boolean rotateLeft, Node<E> parent,Node<E> node){
//        int comp=parent.parent.data.compareTo(parent.parent.parent.data);
//        if(rotateLeft){
//            if(parent.left!=null){
//                parent.parent.right=parent.left;
//            }
//            parent.left=parent.parent;
//            if(comp<0){
//
//            }
//
//        }else{
//            if(node.left!=null){
//                parent.right=node.left;
//            }
//            node.left=parent;
//            parent.parent.right=node;
//        }
    }

    private void splayZigZag(boolean leftPC, Node<E> parent,Node<E> node){

    }

    private void splayZig(Node<E> parent,Node<E> node){

    }

    @Override
    public boolean remove(E x) {
        return false;
    }

    @Override
    public boolean contains(E x) {
        return false;
    }


    //metod för felsökning. Skriver ut splay-träet
    public static void reverseInOrder(Node h, int indent) {
        if (h != null) {
            indent++;
            reverseInOrder(h.right, indent);

            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
            System.out.println(h.data);

            reverseInOrder(h.left, indent);
        }
    }

    public static void main(String[] args){
        SplayTreeSet<Integer> spList=new SplayTreeSet<>();

//        spList.add(new Integer(11));
//
//        spList.add(new Integer(12));
//        spList.add(new Integer(1));
//
//        spList.add(new Integer(9));
//        spList.add(new Integer(0));
//
//        spList.add(new Integer(10));
        spList.add(new Integer(3));

        spList.add(new Integer(2));
        spList.add(new Integer(5));

        spList.add(new Integer(4));
        spList.add(new Integer(7));

        spList.add(new Integer(6));
        spList.add(new Integer(8));

        System.out.println("The root is " + spList.root.data+ ".");

        reverseInOrder(spList.root, 0);
//        while (n != null) {
//            System.out.println(n.data);
//
//            n = n.left;
//        }
        System.out.println();




    }

}
