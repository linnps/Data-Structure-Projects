import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a HashSet of Strings.
 *
 * @author Li-Kai Lin
 * @version 2019-03-03
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   private HashSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      int hammingDistance = 0;
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            hammingDistance++;
         }
      }
      return hammingDistance;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> minLadder = new ArrayList<String>();
      List<String> tempList = new ArrayList<String>();
      start = start.toLowerCase();
      end = end.toLowerCase();
      
      //Return minLadder if start and end are the same word
      if (start.equals(end)) {
         minLadder.add(start);
         return minLadder;
      }
      
      //Return empty minLadder if start and end doesn't have the same word length
      if (getHammingDistance(start, end) == -1) {
         return minLadder;
      }
      
      //Do the breadth-first search for word ladders if start and end are legit words
      if(isWord(start) && isWord(end)) {
         tempList = bfs(start, end);
      }
      
      //Reverse the tempList and get minLadder
      for (int i = tempList.size() - 1; i >= 0; i--) {
         minLadder.add(tempList.get(i));
      }
      
      return minLadder;
   }
   
   
  /** Breadth-first search for word ladders.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder
   */
   private List<String> bfs(String start, String end) {
      List<String> bfsSearch = new ArrayList<String>();
      HashSet<String> visited = new HashSet<String>();
      Deque<Node> queue = new ArrayDeque<Node>();
      
      visited.add(start);
      queue.addLast(new Node(start, null));
      Node last = new Node(end, null);
      
      outerLoop:
      while(!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String oneWord = n.word;
         List<String> words = getNeighbors(oneWord);
         
         for (String w : words) {
            if (!visited.contains(w)) {
               
               visited.add(w);
               queue.addLast(new Node(w, n));
            }
            if (w.equals(end)) {
               last.next = n;
               break outerLoop;
            }
         }
      }
      
      if (last.next == null) {
         return bfsSearch;
      }
         
      Node temp = last;
                  
      while (temp != null) {
         
         bfsSearch.add(temp.word);
         temp = temp.next;
         
      }
      
      return bfsSearch;
   }
   
   private class Node {
      Node next;
      String word;
      
      public Node(String w, Node n) {
         word = w;
         next = n;
      }
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<String>();
      
      
      //Generate the neighbors by making one-char substitutions
      for (int i = 0; i < word.length(); i++) {
      
         StringBuilder temp = new StringBuilder(word);
         char letter = 'a';
         
         for (int j = 0; j < 26; j++) {
         
            //Substitute word[i] with char letter ('a' to 'z')
            temp.setCharAt(i, letter);
            
            //Check if after the character in word[i] is replaced, String word is still a word.
            if (isWord(temp.toString()) && !word.equals(temp.toString())) {
               neighbors.add(temp.toString());
            }
            
            letter = (char)(letter + 1);
         }
      }
      
      return neighbors;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (!isWord(sequence.get(i)) || !isWord(sequence.get(i + 1))) {
            return false;
         }
         if (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1) {
            return false;
         }
      }
      
      return true;
   }

}

