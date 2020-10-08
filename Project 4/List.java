import java.util.Iterator;

/**
 * List.java. Describes the abstract behavior of a list collection.
 * This interface together with one or more subinterfaces describe the behavior
 * of a specific type of list.
 */
public interface List<T> extends Iterable<T> {

   /**
    * Returns the number of elements in this list.
    */
   int size();
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   boolean isEmpty();
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   Iterator<T> iterator();

}
