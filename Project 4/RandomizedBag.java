import java.util.Random;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
* RandomizedBag.
*
* @author Li-Kai Lin
* @version Feb/9/2019
*/
public class RandomizedBag<T> implements RandomizedList<T> {
   ///////////////field/////////////////////
   private int size;
   private T[] elements;
   private static final int originCapacity = 1;
      /////////////////////////////////////////

   //constructors
   /**
    * Constructor 1.
    * @param capacity input.
    */
   @SuppressWarnings("unchecked")
   public RandomizedBag(int capacity) {
   
      elements = (T[]) new Object[capacity];
      
      size = 0;         
   }
   /**
    * Constructor 2.
    */
   public RandomizedBag() {
      this(originCapacity);
   }
   ////////////////////////////////////////////////////////////////////////////
   ////////////////////////methodes////////////////////////////////////////////
   /**
    * Returns size.
    * @return size representing the number of elements
    */
   public int size() {
      return size;
   }
   
   /**
    * Check if its empty.
    *
    * @return true if empty
    */
   public boolean isEmpty() {
      return size == 0;
   }
  
   /**
    * return random elements
    * @return element randomly
    */
   public T sample() {
      
      Random random = new Random();         
      if (isEmpty()) {
         return null;
      }
      return elements[random.nextInt(size)];
   }


   /**
    * Add elements
    * @param element is added
    */
   public void add(T elementIn) {
      if (elementIn == null) {
         throw new IllegalArgumentException();
      }
   
   
         //check the size of the array
      if (size == elements.length) {
         resize(size * 2);
      }
      //store the input element
      elements[size] = elementIn;
      //increment by in for the new element input
      size++;
   }
   
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    *
    * @return the element that is about to be removed
    */
   public T remove() {
      
      Random randomGenerator = new Random();
      
      if (isEmpty()) {
         return null;
      }
      
   
      int randomIndex = randomGenerator.nextInt(size);
   
      T elementRemoved = elements[randomIndex];
   
      size--;
      elements[randomIndex] = elements[size];
      elements[size] = null;
      
      
      if ((size > 0)) {
         if((size < elements.length / 4)) {
         
            resize(elements.length / 2);
         }   
      }
   
      return elementRemoved;
   }
   
   
   /**
    * Returns an iterator over the elements in this list.
    *
    * @return an iterator .
    */
   public Iterator<T> iterator() {
      return new RandomIterator<T>(elements, size);
   }
   
   
   
   ////////////////////////////////////////////////////////////////////////////
   ////////////////////////helper classes///////////////////////////////////
   
   private class RandomIterator<T> implements Iterator<T> {
      
      //fields in the helper class
      private T[] elements;
      private int nextIndex;
      private int arraySize;
      private int front = 0;
   
   /**
    * Construct.
    *
    * @param  elementIn array input
    * @param  sizeIn array size
    */
      public RandomIterator(T[] elementIn, int sizeIn) {
         elements = elementIn;
         nextIndex = 0;
         arraySize = sizeIn;
      }
   /**
    * get rid of the Unsupported remove method in the iterator.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
       
   /**
    * if it has a next element in the array return true
    *
    * @return true if there is one more
    */
      public boolean hasNext() {
         return arraySize > nextIndex;
      }
   /**
    * Returns next item in the array.
    * @return nextElement randomly in the array
    */
      public T next() {
         Random randomGenerator = new Random();         
      
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         int randomIndex = randomGenerator.nextInt(arraySize);
         if (randomIndex < front) {
            randomIndex = randomIndex + front;
         }
         T nextElement = elements[randomIndex];
         
         if (randomIndex != front) {
            elements[randomIndex] = elements[front];
            elements[front] = nextElement;
            
            
         }
         front++;   
         arraySize--;
      
         return nextElement;
      }
   
   }
   /////////////////method for self-used////////////////////////////
   /**
    * Resize the array
    * @param sizeIn array
    */
   @SuppressWarnings("unchecked")
   private void resize(int sizeIn) {
      T[] newArray = (T[]) new Object[sizeIn];
      for (int i = 0; i < size; i++) {
         newArray[i] = elements[i];
      }
      elements = newArray;
   }
   
         
}
