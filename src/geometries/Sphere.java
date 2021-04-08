package geometries;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Sphere implements Geometry
{
	Point3D center;
	double radius;
	public Sphere(Point3D center, double radius) 
	{
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
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		//point and vector of ray
		Point3D p0 = ray.getP0();//ray point
		Vector v = ray.getDir();//ray vector
		//check if ray point is the center
		if(p0.equals(center))       //
			return List.of(ray.getPoint(radius));
		Vector u=center.subtract(p0);// the vector between center and ray
		double tm=v.dotProduct(u); //the scale for the ray in order to get parallel to the sphere center
		double d=Math.sqrt(u.lengthSquared()-tm*tm);//get the distance between the ray and the sphere center
		//check if d is bigger then radius-the ray doesn't cross the sphere
		if (d>radius)
			return null;
		double th=Math.sqrt(radius*radius-d*d);//according pitagoras
		double t1=tm+th;
		double t2=tm-th;
		if(t1>0&&t2>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if orthogonal -> no intersection
			return List.of(ray.getPoint(t1),ray.getPoint(t2));
		else if(t1>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))) //if only t1 is not orthogonal and positive
			return List.of(ray.getPoint(t1));
		else if(t2>0&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v))) //if only t2 is not orthogonal and positive
			return List.of(ray.getPoint(t2));
		else
			return null;
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


}