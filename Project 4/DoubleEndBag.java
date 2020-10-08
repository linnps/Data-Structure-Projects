
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
* DoubleEndBag
*
* @author Li-Kai Lin
* @version Feb/9/2019
*/
public class DoubleEndBag<T> implements DoubleEndedList<T> {
   //field
   private Node front;
   private Node rear;
   private int size;
   
   //constructor
      /**
       * DoubleEndBag constructor.
       */
   public DoubleEndBag() {
      front = null;
      rear = null;
      size = 0;
   }
   //methods
   /**
    * check if it is empty.
    */
   public boolean isEmpty() {
      boolean equality = (size == 0);
      return equality;
   }
   /**
    * size.
    */
   public int size() {
      return size;
   }
   /**
    * Add to front.
    */
   public void addFirst(T elementIn) {
      //check if input is empty
      if (elementIn == null) {
         throw new IllegalArgumentException();
      }
      //maka a node object
      Node addedNode = new Node(elementIn);
      //check if there is element in the bag
      if (front == null) {
         front = addedNode;
         
         rear = addedNode;
      }
      //if there are other elements
      else {
         front.prev = addedNode;
         
         addedNode.next = front;
         
         front = addedNode;
      }
      size++;
   }
    
   /**
    * Add to end.
    */
   public void addLast(T elementIn) {
      
      //check if input is empty
      if (elementIn == null) {
         throw new IllegalArgumentException();
      }
      //maka a node object
      Node addedNode = new Node(elementIn);
      //check if there is element in the bag
      if (front == null) {
         front = addedNode;
         
         rear = addedNode;
      }
      //if there are other elements
      else {
         rear.next = addedNode;
         
         addedNode.prev = rear;
         
         rear = addedNode;
      }
      size++;
   }
   
   /**
    * remove the front. 
    */
   public T removeFirst() {
      //check if input is empty
      if (isEmpty()) {
         return null;
      }
      //maka a node object
      T elementRemoved = front.element;
      //check if there is only one element in the bag
      if (size == 1) {
      
         rear =null;
         
         front = null;
      }
      //if there are other elements
      else {
         front = front.next;
         
         front.prev = null;
      }
      size--;
      return elementRemoved;
   }
    
   /**
    * remove the end.
    */
   public T removeLast() {
      //check if input is empty
      if (isEmpty()) {
         return null;
      }
      //maka a node object
      T elementRemoved = rear.element;
      //check if there is only one element in the bag
      if (size == 1) {
      
         rear =null;
         
         front = null;
      }
      //if there are other elements
      else {
         rear = rear.prev;
         
         rear.next = null;
      }
      
      size--;
      return elementRemoved;         
   }

      
      
      
      
   /////////////////////////
   /**
    * iterator for node.
    */
   public Iterator<T> iterator() {
      return new DoubleEndIterator();
   }
   
   ///////////////////////Nested class///////////////////////////////
   /**
    * Nested Node class.
    */
   private class Node {
      private Node prev;
      
      private Node next;
   
      private T element;
   
      //constructors for Node
      public Node(T e) {
         
         element = e;
      }
      public Node(T e, Node n) {
         
         next = n;
         element = e;
      }
      public Node(T e, Node n, Node m) {
         
         next = n;
         element = e;
         prev = m;
         
      }
   }
   
   ///self-made class for iterator
   /**
    * Nested DoubleEndIterator class.
    */
   private class DoubleEndIterator implements Iterator<T> {
      private Node current = front;
      
   
      //unsupported
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public boolean hasNext() {
         return (current != null);
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T storedValue = current.element;
         current = current.next;
         
         
         return storedValue;
      }
    
    
   }
}
