package geometries;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Sphere extends Geometry
{
	Point3D center;
	double radius;
	public Sphere(Point3D center, double radius) 
	{//if !
		super();
		this.center = center;
		this.radius = radius;
	}
	
///////////////////// get  //////////////////
	public Vector getNormal(Point3D p) 
	{
		return center.subtract(p).normalized();//the normal is the subtraction of the center from the point

	}
	public Point3D getCenter() 
	{
		return center;
	}

	public double getRadius() 
	{
		return radius;
	}

//////////// intersections ////////////////
	/**
	 * @param ray
	 * @return a list of GeoPoints- intersections of the ray with the sphere, and this sphere
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		//point and vector of ray
		Point3D p0 = ray.getP0();//ray point
		Vector v = ray.getDir();//ray vector
		//check if ray point is the center,there'll be only 1 intersection found by the dir and radius
		if(p0.equals(center))       
			return List.of(new GeoPoint(this,ray.getPoint(radius)));//return the intersection point
		Vector u=center.subtract(p0);// the vector between center and ray
		double tm=v.dotProduct(u); //the scale for the ray in order to get parallel to the sphere center
		double d=Math.sqrt(u.lengthSquared()-tm*tm);//get the distance between the ray and the sphere center
		//check if d is bigger then radius-the ray doesn't cross the sphere
		if (d>radius)
			return null;
		double th=Math.sqrt(radius*radius-d*d);//according to Pitagoras
		double t1=tm+th;
		double t2=tm-th;
		if(t1>0 && t2>0 &&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v)) && !isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if orthogonal -> no intersection
			return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
		else if(t1>0 && !isZero(ray.getPoint(t1).subtract(center).dotProduct(v))) //if only t1 is not orthogonal and positive
			return List.of(new GeoPoint(this,ray.getPoint(t1)));
		else if(t2>0&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if only t2 is not orthogonal and positive
		 	return List.of(new GeoPoint(this,ray.getPoint(t2)));
		else
			return null;//no intersections
	}

//////////////////admin ///////////////////
@Override
public String toString() 
{
	return "Sphere [center=" + center + ", radius=" + radius + "]";
}
/**
* Returns All intersections with ray
*
* @param ray The ray
* @return List of intersections (Points)
* @see Point3D#Point3D(Coordinate, Coordinate, Coordinate)
* @see Ray#Ray(Point3D, Vector)
*/
/////mini 2
@Override
protected void CreateBoundingBox() {
	minX = center.getX() - radius;
	maxX = center.getX() + radius;
	minY = center.getY() - radius;
	maxY = center.getY() + radius;
	minZ = center.getZ() - radius;
	maxZ = center.getZ() + radius;
	middleBoxPoint = center;
	finityShape = true;
}

}