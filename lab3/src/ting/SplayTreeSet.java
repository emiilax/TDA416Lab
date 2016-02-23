package ting;


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

    public SplayTreeSet() {
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {

        if (root == null) {
            root = new Node(x, null, null, null);
            size++;
            return true;
        }

        return addRec(root, x);
    }


    private boolean addRec(Node<E> node, E x) {

        int comp = x.compareTo(node.elt);


        if (comp == 0) {
            return false;

        } else if (comp > 0) {

            if (node.right == null) {
                node.right = new Node(x, node, null, null);
                size++;
                if (size > 1) splayElement(node.right);

                return true;
            }

            return addRec(node.right, x);

        } else {
            if (node.left == null) {
                node.left = new Node(x, node, null, null);
                size++;
                if (size > 1) splayElement(node.left);

                return true;
            }

            return addRec(node.left, x);
        }


    }

    @Override
    public boolean remove(E x) {

        return removeRec(root, x);

    }

    private boolean removeRec(Node<E> node, E x) {

        if (node == null) return false;

        int comp = x.compareTo(node.elt);

        if (comp == 0) {

            if (node != root) {
                splayElement(node);
            }
            Node s=node.left;
            Node t=node.right;
            if(s==null && t==null) {
                root=null;
            } else if(s==null){
                t.parent=null;
                root=t;
            } else if (t==null) {
                s.parent=null;
                root=s;
            } else {
                s.parent = null;
                t.parent = null;
                root = s;
                Node newRoot = findMax(s);
                splayElement(newRoot);
                newRoot.right = t;
                t.parent = newRoot;
            }

//            if (node.left != null) {
//                Node<E> n = deleteMaxLeft(node.left);
//                node.elt = n.elt;
//            }
//
//            if (node.right != null) {
//                root = node.right;
//                node.right.parent = null;
//            }
//            if (root == node && size == 1) root = null;



            size--;
            return true;

        } else if (comp < 0) {
            return removeRec(node.left, x);
        }

        return removeRec(node.right, x);

    }

    private Node<E> findMax(Node<E> node){
        if(node==null){
            return null;
        }

        if(node.right==null){
            return node;
        } else{
            return findMax(node.right);
        }
    }

    private Node<E> deleteMaxLeft(Node<E> node) {
        if (node.right == null) {
            node.parent.left = node.left;
            node.right=node.parent.right;
            node.parent = null;
            root=node;
            return node;
//            node.right=node.parent.right;
//            node.right.parent=node;
//            node.parent=null;
        }

        return deleteMaxLeft(node.right);

    }

    @Override
    public boolean contains(E x) {

        return containsRec(root, x);

    }

    private boolean containsRec(Node<E> node, E x) {

        if (node == null) return false;

        int comp = x.compareTo(node.elt);
        if (comp == 0) {
            splayElement(node);
            return true;
        } else if (comp < 0) {
            return containsRec(node.left, x);
        }
        return containsRec(node.right, x);
    }

    private void splayElement(Node<E> node) {

        if (node == root) {
            //System.out.println("return");
            return;
        }

        if (node.parent.parent == null) {
            if (node.parent.right == node) {
                rotateLeft(node);
            } else if (node.parent.left == node) {
                rotateRight(node);
            }
        } else if ((node.parent.right == node) && (node.parent.parent.right == node.parent)) {
            //zig-zig to left
            rotateLeft(node.parent);
            rotateLeft(node);
        } else if ((node.parent.left == node) && (node.parent.parent.left == node.parent)) {
            //zig-zig to right
            rotateRight(node.parent);
            rotateRight(node);
        } else if ((node.parent.left == node) && (node.parent.parent.right == node.parent)) {
            //zig-zag right-left
            rotateRight(node);
            rotateLeft(node);
        } else if ((node.parent.right == node) && (node.parent.parent.left == node.parent)) {
            //zig-zag left-right
            rotateLeft(node);
            rotateRight(node);
        }

        splayElement(node);

    }

    private void rotateRight(Node<E> node) {
        node.parent.left = node.right;

        if (node.right != null) {
            node.right.parent = node.parent;
        }

        node.right = node.parent;

        if (node.parent.parent != null) {

            if (node.parent.parent.right == node.parent) {
                node.parent.parent.right = node;
            } else if (node.parent.parent.left == node.parent) {
                node.parent.parent.left = node;
            }
            //node.parent = node.parent.parent;
        }

        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;
//        root=newParent;
        if (node.parent == null) {
            root = node;
        }

    }

    private void rotateLeft(Node<E> node) {
        node.parent.right = node.left;
        if (node.left != null) {
            node.left.parent = node.parent;
        }

        node.left = node.parent;


        if (node.parent.parent != null) {

            if (node.parent.parent.right == node.parent) {
                node.parent.parent.right = node;

            } else if (node.parent.parent.left == node.parent) {
                node.parent.parent.left = node;
            }
            //node.parent = node.parent.parent;
        }

        Node newParent = node.parent.parent;
        node.parent.parent = node;
        node.parent = newParent;
        root=newParent;
//      newP.left=node;
        if (node.parent == null) {
            root = node;
        }



    }

//    metod för felsökning. Skriver ut splay-träet
    public static void reverseInOrder(Node h, int indent) {
        if (h != null) {
            indent++;
            reverseInOrder(h.right, indent);

            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
            System.out.println(h.elt);

            reverseInOrder(h.left, indent);
        }
    }

    public static void main(String[] args){
        SplayTreeSet<Integer> sts = new SplayTreeSet<>();

        sts.contains(10);
        reverseInOrder(sts.root,0);
        System.out.println("1-----------");

        sts.contains(18);
        reverseInOrder(sts.root,0);
        System.out.println("2-----------");


        sts.add(5);
        reverseInOrder(sts.root,0);
        System.out.println("3-----------");


        sts.add(2);
        reverseInOrder(sts.root,0);
        System.out.println("4-----------");


        sts.contains(16);
        reverseInOrder(sts.root,0);
        System.out.println("5-----------");


        sts.contains(12);
        reverseInOrder(sts.root,0);
        System.out.println("6-----------");


        sts.contains(1);
        reverseInOrder(sts.root,0);
        System.out.println("7-----------");


        sts.remove(0);
        reverseInOrder(sts.root,0);

        System.out.println("8-----------");

        sts.contains(13);
        reverseInOrder(sts.root,0);

        System.out.println("9-----------");

        sts.contains(2);
        reverseInOrder(sts.root,0);
        System.out.println("10-----------");

        sts.add(0);
        reverseInOrder(sts.root,0);
        System.out.println("11-----------");

        sts.add(7);
        reverseInOrder(sts.root,0);
        System.out.println("12-----------");

        sts.add(1);
        reverseInOrder(sts.root,0);
        System.out.println("13-----------");

        sts.add(18);
        reverseInOrder(sts.root,0);
        System.out.println("14-----------");

        sts.remove(18);
        reverseInOrder(sts.root,0);
        System.out.println("15-----------");

        sts.remove(9);
        reverseInOrder(sts.root,0);
        System.out.println("16-----------");

        sts.remove(5);
        reverseInOrder(sts.root,0);
        System.out.println("17-----------");

        sts.add(17);
        reverseInOrder(sts.root,0);
        System.out.println("18-----------");


        System.out.println(sts.add(2));

    }

}
