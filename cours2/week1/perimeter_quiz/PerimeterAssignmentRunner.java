import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point p : s.getPoints() ){
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avgDistance = 0.0;
        Point compareTo = s.getLastPoint();
        for(Point p : s.getPoints()){
            avgDistance = avgDistance + p.distance(compareTo);
            compareTo = p;
        }
        return avgDistance/this.getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double maxDistance = 0.0;
        Point compareTo = s.getLastPoint();
        double currentDistance;
        for(Point p : s.getPoints()){
            currentDistance= p.distance(compareTo);
            if(currentDistance > maxDistance){
                maxDistance = currentDistance;
                }
            compareTo = p;
        }
        return maxDistance;
    }

    public double getLargestX(Shape s) {
        double maxX = 0.0;
        double currentX;
        for(Point p : s.getPoints()){
            currentX=p.getX();
        if(currentX>maxX){
         maxX=   currentX;
        }
    }
    return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0.0;
        double currentPerimeter;
        for(File  f : dr.selectedFiles()){
            Shape s = new Shape(new FileResource(f));
            currentPerimeter = this.getPerimeter(s);
            if(currentPerimeter >= maxPerimeter){
                maxPerimeter=currentPerimeter;
            }
        }
        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
     
        File temp = null;  
         DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0.0;
        double currentPerimeter;
        for(File  f : dr.selectedFiles()){
            Shape s = new Shape(new FileResource(f));
            currentPerimeter = this.getPerimeter(s);
            if(currentPerimeter >= maxPerimeter){
                maxPerimeter=currentPerimeter;
                temp = f;
            }
        }
        // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("Number of points = " + this.getNumPoints(s));
        System.out.println("Avg of points = " + this.getAverageLength(s));
        System.out.println("Max distance = " + this.getLargestSide(s));
        System.out.println("Max x = " + this.getLargestX(s));
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }

    public void testPerimeterMultipleFiles() {
        System.out.println("Max perimeter = " + this.getLargestPerimeterMultipleFiles());
        
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File with max perimeter = " + this.getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
