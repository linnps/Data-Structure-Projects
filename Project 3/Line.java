import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author  Li-Kai Lin
 * @version 2019-02-03
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** 
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /** 
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    */
   public Line(Collection<Point> c) {
      this();
      for (Point pt : c) {
         this.add(pt);
      }
   }
   
   /** 
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    */
   public boolean add(Point p) {
      if (locate(p)) {
         return false;
      }
      else if (this.length() == 1 || this.length() == 0) {
         line.add(p);
      }
      else {
         double slope = first().slopeTo(last());
         if (slope == p.slopeTo(last())) {
            line.add(p);
         }  
      }
      return true;
   }
   
   /** 
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    */
   public Point first() {
      if (length() == 0) {
         return null;
      }
      else {
         return this.line.first();
      } 
   }
   
   /** 
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    */
   public Point last() {
      if (length() == 0) {
         return null;
      }
      else {
         return this.line.last();
      } 
   }
   
   /** 
    * Returns the number of points in this line.
    */
   public int length() {
      int len = 0; 
      for (Point point : line) {
         len++;
      }
      return len;
   }


   @Override
   public int compareTo(Line that) {
      if (length() == 0 && that.length() == 0) {
         return 0;
      }
      else if (length() != 0 && that.length() == 0) {
         return 1;
      }
      else if (length() == 0 && that.length() != 0) {
         return -1;
      }
      else if (first().equals(that.first()) && last().equals(that.last())) {
         return 0;
      }
      else if ((first().compareTo(that.first()) > 0) || last().compareTo(that.last()) > 0
                && first().equals(that.first())) {
         return 1;
      }
      else {
         return -1;
      }
   }

   /** 
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
      Iterator<Point> ir = this.line.iterator();
      return ir;
   }
   
   /** 
    * Return true if this line's first and last points are equal to the
    * parameter's first and last points. Empty lines are equal to each other
    * but are not equal to any non-empty line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override 
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first())) && (this.last().equals(that.last()));
   }
 
   /** 
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }
   
   
   
   
   //locate the point in a line.   
   private boolean locate(Point p) {
      for (Point pt : line) {
         if (pt.equals(p)) {
            return true;
         }
      }
      return false;
   }      
 
}
