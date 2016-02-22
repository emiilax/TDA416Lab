package lime;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.util.Random;

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
            }

            if(node.right != null){
                root = node.right;
                node.right.parent = null;
            }
            if(root == node && size == 1) root = null;

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


        sts.remove(7);
        sts.add(13);
        sts.remove(13);
        sts.add(8);
        sts.remove(8);
        sts.add(17);
        sts.remove(17);
        sts.remove(14);
        sts.add(9);
        sts.remove(9);
        sts.add(4);
        sts.remove(4);
        System.out.println(sts.remove(13));
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
