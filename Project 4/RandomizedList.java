
/**
 * RandomizedList.java. Describes the abstract behavior of a
 * randomized list collection.
 */
public interface RandomizedList<T> extends List<T> {
 
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   void add(T element);
  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    */
   T remove();
   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
   T sample();
   
}