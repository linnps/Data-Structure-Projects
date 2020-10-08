import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Li-Kai Lin
 *
 */
public final class Selector {


   private Selector() { }



   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      
      Iterator<T> iter = coll.iterator();
      
      T answer = iter.next();
      while (iter.hasNext()) {
         
         T t1 = iter.next();
         
         if (comp.compare(t1, answer) < 0) {
            answer = t1;
         }
      }
      
      
      return answer;         
   }


   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      
      Iterator<T> iter = coll.iterator();
      
      T answer = iter.next();
      while (iter.hasNext()) {
         
         T t1 = iter.next();
         
         if (comp.compare(t1, answer) > 0) {
            answer = t1;
         }
      }
      
      
      return answer;                
   }



   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty() || k < 1 || coll.size() < k) {
         throw new NoSuchElementException("Error.");
      }
      
      List<T> newList = new ArrayList<>(coll);
      java.util.Collections.<T>sort(newList, comp);
      
      if (newList.size() == 1) {
         return newList.get(0);
      }
      int i = 1;
      int diff = 1;
      while ( diff < k && i < newList.size()) {
         if (comp.compare(newList.get(i), newList.get(i - 1)) != 0) {
            diff++;
         }
         i++;
      }
      
      if (k == diff) {
         return newList.get(i - 1);
      }
      else {
         throw new NoSuchElementException("Error");         
      }
   }



   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty() || k < 1 || coll.size() < k) {
         throw new NoSuchElementException("Error.");
      }
      
      
      List<T> newList = new ArrayList<>(coll);
      java.util.Collections.<T>sort(newList, comp);
      if (newList.size() == 1) {
         return newList.get(0);
      }
      int i = newList.size() - 1;
      int diff = 1;
      while ((diff < k) && (i > 0)) {
         if (comp.compare(newList.get(i), newList.get(i - 1)) != 0) {
            diff++;
         }
         i--;
      }
      
      if (k == diff) {
         return newList.get(i);
      }
      else {
         throw new NoSuchElementException("Error");         
      }
   }



   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      Collection<T> answer = new ArrayList<T>();
   
      
      Iterator<T> iter = coll.iterator();
               
      while (iter.hasNext()) {
         
         T t1 = iter.next();
         
         if (comp.compare(t1, low) >= 0 
            && comp.compare(t1, high) <= 0) {
            answer.add(t1);
         }
      }         
      
   
   
      if (answer.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      return answer;        
   }



   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      T maxValue = Selector.max(coll, comp);
      Collection<T> valueInRange = Selector.range(coll, key, maxValue, comp);
      
      
      
      if (valueInRange == null) {
         throw new NoSuchElementException("Error.");
      }
      
      return Selector.min(valueInRange, comp); 
   }



   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Error.");
      }
      else if (coll.isEmpty()) {
         throw new NoSuchElementException("Error.");
      }
      T minValue = Selector.min(coll, comp);
      Collection<T> valueInRange = Selector.range(coll, minValue, key, comp);
      
      
      
      if (valueInRange == null) {
         throw new NoSuchElementException("Error.");
      }
      
      return Selector.max(valueInRange, comp); 
   }

}
