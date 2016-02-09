package lime;

/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {

  public class Node {
    /** The contents of the node is public */
    public E elt;

    protected Node prev, next;

    Node() {
      this(null);
    }

    Node(E elt) {
      this.elt = elt;
      prev = next = null;
    }

    public Node getNext() {
      return this.next;
    }

    public Node getPrev() {
      return this.prev;
    }
  }
  
  /** first and last nodes in list, null when list is empty */
  Node first, last;
  
  DLList() {
    first = last = null;
  }
  
  /** inserts an element at the beginning of the list
   * @param e   the new element value
   * @return    the node holding the added element
   */
  public Node addFirst(E e) {

    Node node = new Node(e);

    if (first == null) {
      first = node;
      last = node;
    } else {
      node.next = first;
      first.prev = node;
      first = node;
    }

    return node;

  }

  /** inserts an element at then end of the list
   * @param e   the new element
   * @return    the node holding the added element
   */
  public Node addLast(E e) {

    Node node = new Node(e);

    if (last == null) {
      last = node;
      first = node;
    } else {
      node.prev = last;
      last.next = node;
      last = node;
    }
    return node;

  }
  
  /**
   * @return    the node of the list's first element, null if list is empty
   */
  public Node getFirst() {
    return first;
  }
  
  /**
   * @return    the node of the list's last element, null if list is empty
   */
  public Node getLast() {
    return last;
  }
  
  /** inserts a new element after a specified node
    * @param e   the new element
    * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertAfter(E e, Node l) {

    Node node = new Node(e);
    node.prev = l;
    node.next = l.next;

    l.next.prev = node;
    l.next = node;

    if(node.next == null) last = node;

    return node;
  }

  /** inserts a new element before a specified node
    * @param e   the new element
    * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertBefore(E e, Node l) {

    Node node = new Node(e);
    node.next = l;
    node.prev = l.prev;

    l.prev.next = node;
    l.prev = node;

    if(node.prev == null) first = node;

    return node;
  }

  /** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  public void remove(Node l) {

    if(l.next!=null){
      l.next.prev=l.prev;
    }
    if (l.prev!=null){
      l.prev.next=l.next;
    }

    if (l.equals(first)) {
      first = l.next;
    }
    if (l.equals(last)) {
      last = l.prev;
    }


  }

}
