package K_Means;

import java.util.Random;

import geometries.*;
import primitives.*;

/**
 * @author Avital & Tal
 */
public class Point {
	private Geometry geometry;
    private Point3D PositionPoint;
    private int clusterNumber = 0;

    public Point(Geometry g)
    {
        this.geometry=g;
        this.PositionPoint=g.getPositionPoint();
    }
   
    /**
	 * @return the PositionPoint
	 */
	public Point3D getPositionPoint() {
		return PositionPoint;
	}
    /**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	/**
	 * @param geometry 
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	/**
	 * @param PositionPoint the point to set
	 */
	public void setPositionPoint(Point3D positionPoint) {
		PositionPoint = positionPoint;
	}
	/**
	 * @return the clusterNumber
	 */
	public int getClusterNumber() {
		return clusterNumber;
	}
	/**
	 * @param clusterNumber 
	 */
	public void setClusterNumber(int cluster_number) {
		this.clusterNumber = cluster_number;
	}
	//Distance between two position_points
    protected static double distance(Point p, Point centroid) {
    	return p.getPositionPoint().distanceSquared(centroid.getPositionPoint());
    }
    //Creates random point
    protected static Point createRandomPoint() {
    	return Point.createRandomPoint(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }    
    protected static Point createRandomPoint(double min, double max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        double z = min + (max - min) * r.nextDouble();
        return new Point(new Sphere(new Point3D(x,y,z),1));
    }
}
