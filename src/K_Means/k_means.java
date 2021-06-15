package K_Means;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;

/**
 * @author Avital & Tal
 *
 */

public class k_means {

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;    
    private int MAX_ITERATIONS = 100;

    private List<Point> points;
    private List<Cluster> clusters;

    public k_means() {
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList<Cluster>();        
    }
    
    /**
	 * @return the clusters
	 */
	public List<Cluster> getClusters() {
		return clusters;
	}

	//Initializes the process
    public void init(List<Point> points) {
        //add Points
        this.points = points;

        //Create Clusters
        //Set random centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(-100,100);
            cluster.setCentroid(centroid);
            this.clusters.add(cluster);
        }
    }

    //The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish && iteration < MAX_ITERATIONS) {
            //Clear cluster state
            clearClusters();
            List<Point> lastCentroids = getCentroids();
            //Assign points to the closer cluster
            assignCluster();
            //Calculate new centroids.
            calculateCentroids();
            iteration++;
            List<Point> currentCentroids = getCentroids();
            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
            if(distance == 0) { //if everything is as before - finish
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for(Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<Point>(NUM_CLUSTERS);
        for(Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getGeometry());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 

        for(Point point : points) {
            min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setClusterNumber(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            double sumZ = 0;
            List<Point> listPoints = cluster.getPoints();
            int n_points = listPoints.size();

            for(Point point : listPoints) {
                sumX += point.getPositionPoint().getX().get();
                sumY += point.getPositionPoint().getY().get();
                sumZ += point.getPositionPoint().getZ().get();}

            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
                double newX = sumX / n_points;
                double newY = sumY / n_points;
                double newZ = sumZ / n_points;
                Point3D newCentroid=new Point3D(newX,newY,newZ);
                centroid.setPositionPoint(newCentroid);
            }
        }
    }
}
