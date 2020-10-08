
/**
 * Provides a factory method for creating word search games. 
 *
 * @author Li-Kai Lin
 * @version 2/17/2019
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null.
      return new SearchWords();
   }

}
