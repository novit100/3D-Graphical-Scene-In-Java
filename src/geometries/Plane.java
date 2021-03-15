package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
Point3D q0;
Vector normal;
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
@Override
public String toString() 
{
	return "Plane [q0=" + q0 + ", normal=" + normal + "]";
}

	
}
