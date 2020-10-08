import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Li-Kai Lin
 * @version 2019-02-03
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
      try {
         Scanner f1 = new Scanner(new File(filename));
            //read the first line of the file
         int numPoint = f1.nextInt();
            //instantiate the point array
         points = new Point[numPoint];
         int i = 0;
         while (i < numPoint) {
            int x = f1.nextInt();
            int y = f1.nextInt();
               //instantiate a point
            points[i] = new Point(x, y);
            i++;
         }
      }   
      catch (Exception e) {
         System.out.println("ERROR");
      }
   
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      
      lines = new TreeSet<Line>();
      
      if (points.length < 4) {
         return lines;
      }
   
      for (int i = 0; i < points.length; i++) {
         
         for (int j = i + 1; j < points.length; j++) {
            
            for (int k = j + 1; k < points.length; k++) {
               
               for (int l = k + 1; l < points.length; l++) {
                  //save array as a List
                  Collection<Point> collecOfPoints 
                     = Arrays.asList(points[i], points[j], points[k], points[l]);
                  //instanciate line
                  Line line = new Line(collecOfPoints);
                  // check if the number of the points of a line is 4
                  if (line.length() == 4) {
                     lines.add(line);
                  }
               }
            }
         }
      }
      
      return lines;
   }
   
   //tool method
   private Line lineBuilder(Point[] poWithSameSl, Point firstP, int startInd, int endInd) {
      Line line = new Line();
      line.add(firstP);
      for (int i = startInd; i <= endInd; i++) {
         line.add(poWithSameSl[i]);
      }
      return line;
   }     
   
   

   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      if (points.length < 4) {
         return lines;
      }
      Point[] pointSl = Arrays.<Point>copyOf(points, points.length);
      //ascending order
      Arrays.sort(points);
      for (int i = 0; i < points.length; i++) {
         Arrays.<Point>sort(pointSl, points[i].slopeOrder);
         double[] sl = new double[pointSl.length];
         for (int j = 0; j < sl.length; j++) {
            sl[j] = pointSl[j].slopeTo(points[i]);
         }
         //begin
         int beg = 0;
         //ending
         int ed = 0;
         //current points
         int now = 0;
         while (now < pointSl.length - 1) {
            if (pointSl[now].compareTo(points[i]) < 0) {
               while (now + 1 < pointSl.length 
                        && pointSl[now].slopeTo(points[i]) == pointSl[now + 1].slopeTo(points[i])) {
                  now++;
               }
               beg = now;
               ed = now;
               if (now == pointSl.length - 1) {
                  break;
               }
            }
            //m1 is the first slope
            double m1 = pointSl[now].slopeTo(points[i]);
            //m2 is the second slope
            double m2 = pointSl[now + 1].slopeTo(points[i]);
            if (m1 == m2) {
               ed++;
            }
            else {
               if (ed - beg + 1 >= 3) {
                  lines.add(lineBuilder(pointSl, points[i], beg, ed));
               }
               beg = ++ed;
            }
            now++;
         }
         if (ed - beg + 1 >= 3) {
            lines.add(lineBuilder(pointSl, points[i], beg, ed));
         }
      }
      return lines;
   }


   
}
