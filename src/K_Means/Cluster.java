package K_Means;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
 public class Cluster {

    public List<Point> points;
    public Point centroid;
    public int id;

    //Creates a new Cluster
    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList<Point>();
        this.centroid = null;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(List<Point> points) {
    	for(Point p :points){
    		points.add(p);
    	}
    }
    public void addPoint(Point p) {   	
    		points.add(p);    	
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

}
