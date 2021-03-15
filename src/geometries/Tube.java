package geometries;
import primitives.*;

public class Tube 
{
Ray axisRay;
double radius;

public Tube(Ray axisRay, double radius) 
{
	super();
	this.axisRay = axisRay;
	this.radius = radius;
}

public Vector getNormal(Point3D p) 
{
	return null;
}

public Ray getAxisRay() 
{
	return axisRay;
}

public double getRadius() 
{
	return radius;
}

@Override
public String toString() 
{
	return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
}




}