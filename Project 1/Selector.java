import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Li-Kai Lin
* @version  2019-01-19
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }
      int min = a[0];
      for (int i = 1; i < a.length; i++) {
         
         if (a[i] < min) {
            min = a[i];
         }
      }
      
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }
      int max = a[0];
      for (int i = 1; i < a.length; i++) {
         
         if (a[i] > max) {
            max = a[i];
         }
      }
      
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("Error.");   
      }
      
      //duplicate a new array dArray
      int[] dArray = new int[a.length];
   
      for (int i = 0; i < a.length; i++) {
         dArray[i] = a[i];  
      }
      //sort the elements in the array (small to large)
      Arrays.sort(dArray);
      
   
      // calculate the number of the elements
      // without repeat.
      int realLength = 1;
      for (int i = 0; i < dArray.length - 1; i++) {
         if (dArray[i] != dArray[i + 1]) {
            realLength++;
         } 
      }
      
      //copy unrepeated elements to a new array
      //rArray
      
      int[] rArray = new int[realLength];
      int rIndex = 0;
      rArray[0] = dArray[0];
      for (int i = 1; i < dArray.length; i++) {
         if (dArray[i] != dArray[i - 1]) {
            rIndex++;
            rArray[rIndex] = dArray[i];
            
         } 
      }
      if (k > rArray.length) {
         throw new IllegalArgumentException("Error.");   
      }
      return rArray[k - 1];
   
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("Error.");   
      }
      
      //duplicate a new array dArray
      int[] dArray = new int[a.length];
   
      for (int i = 0; i < a.length; i++) {
         dArray[i] = a[i];  
      }
      //sort the elements in the array (small to large)
      Arrays.sort(dArray);
      
      //reverse the array in descending and save it
      // in another array eArray
      int[] eArray = new int[a.length];
      int dIndex = 0;
      for (int i = a.length - 1; i >= 0 ; i--) {
         eArray[dIndex] = dArray[i]; 
         dIndex++; 
      }
      
      // calculate the number of the elements
      // without repeat.
      int realLength = 1;
      for (int i = 0; i < eArray.length - 1; i++) {
         if (eArray[i] != eArray[i + 1]) {
            realLength++;
         } 
      }
      
      //copy unrepeated elements to a new array
      //rArray
      
      int[] rArray = new int[realLength];
      int rIndex = 0;
      rArray[0] = eArray[0];
      for (int i = 1; i < eArray.length; i++) {
         if (eArray[i] != eArray[i - 1]) {
            rIndex++;
            rArray[rIndex] = eArray[i];
            
         } 
      }
      if (k > rArray.length) {
         throw new IllegalArgumentException("Error.");   
      }
      return rArray[k - 1];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }         
      int arrayCount = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high && a[i] >= low) {
            arrayCount++;
         }
      }
      
      int[] b = new int[arrayCount];
      int j = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high && a[i] >= low) {
            b[j] = a[i];
            j++;
         }
      }
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }         
      int count = 0;
      int cLing = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            cLing = a[i];
            count++;
         }
         else {
            continue;
         }       
      }
      
      for (int i = 0; i < a.length; i++) {
         if ( a[i] >= key && a[i] <= cLing) {
            cLing = a[i];
         } 
      }
      
      if (count == 0) {
         throw new IllegalArgumentException("Error.");   
      }         
               
      return cLing;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error.");   
      }         
      int count = 0;
      int fl = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            fl = a[i];
            count++;
         }
         else {
            continue;
         }       
      }
      
      for (int i = 0; i < a.length; i++) {
         if ( a[i] >= fl && a[i] <= key) {
            fl = a[i];
         } 
      }
      
      if (count == 0) {
         throw new IllegalArgumentException("Error.");   
      }         
               
      return fl;
   }
   

}
