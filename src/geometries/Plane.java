package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
public class Plane implements Geometry{
	Point3D q0;
	Vector normal;
	
	
	////////////// constructors  /////////////
	public Plane(Point3D q0, Vector normal) 
	{
		super();
		this.q0 = q0;
		this.normal = normal;
	}
	public Plane(Point3D p1, Point3D p2, Point3D p3) 
	{
		super();

		try 
		{
			this.q0=p1;
			Vector v1=(p1.subtract(p2));
			Vector v2=(p1.subtract(p3));		
			this.normal=v1.crossProduct(v2).normalized();//finding the normal of the plane
		}

		catch (IllegalArgumentException exc)
		{
			throw new IllegalArgumentException("This is not a Plane/Triangle");
		}
	}
	/////////////// get /////////////////////
	public Vector getNormal(Point3D p) {
		return normal;
	}

	public Point3D getQ0() 
	{
		return q0;
	}
	public Vector getNormal() 
	{
		return normal;
	}	
	////////////// admin //////////////////////
	@Override
	public String toString() 
	{
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}
	/**@param ray
	 * @return list of points (intersections between the ray and plane)  
	 double t = (_vecNormal.dotProduct(_p.substract(rayP))) / (_vecNormal.dotProduct(rayV));

		            if(isZero(t))
		            	// the ray starts on the plane
		               return null;

	 */
	
	
		/////////// find intersection //////////
	/**
	 * @param ray
	 * @return a list of intersections of the ray with the plane
	 * public List<Point3D> findIntsersections(Ray ray)
	{
		try {
			Vector vec=q0.subtract(ray.getP0());//creating a new vector according to the point q0 and the starting point of the ray (P0)
			double t=normal.dotProduct(vec);//dot product between the vector we created and the normal of the plane
			}
			
		catch(IllegalArgumentException ex) {
			return null;
		}

		

		if(isZero(normal.dotProduct(ray.getDir()))) //if the dot product equals 0 it means that the ray is parallel (makbil) to the plane
			return null;
		t=t/(normal.dotProduct(ray.getDir()));
		if(t==0) {//if the distance between the begining point of the ray and the plane so don't count it an an intersection
			return null;//no itersections
		}
		Point3D p=ray.getPoint(t);
		if(isZero(normal.dotProduct(q0.subtract(p)))) {
			return List.of(p);
		}
		else {
			return null;

		}

	 */

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		try {
			Vector vec=q0.subtract(ray.getP0());//creating a new vector according to the point q0 and the starting point of the ray (P0)

			double t=normal.dotProduct(vec);//dot product between the vector we created and the normal of the plane

			if(isZero(normal.dotProduct(ray.getDir()))) //if the dot product equals 0 it means that the ray is parallel (makbil) to the plane
				return null;
			t=t/(normal.dotProduct(ray.getDir()));

			if(t==0) //if the distance between the p0-the start point of the ray and the plane is 0, its not counted in the intersections list
				return null;//no intersections
			else if(t > 0) // the ray crosses the plane
			{
				Point3D p=ray.getPoint(t);//get the new point on the ray, multiplied by the scalar t. p is the intersection point.
				return List.of(p);//if so, return the point- the intersection
			}
			else // the ray doesn't cross the plane
				return null;	
		}
		catch(Exception ex) {
			return null;
		}
	}

}
