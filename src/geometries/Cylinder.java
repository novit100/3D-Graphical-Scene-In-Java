/**
 * package geometries;

import primitives.*;
public class Cylinder  extends Tube{
	double height;
	////
	public Cylinder(double radius, Ray ray, double height)
	{
		super(ray,radius);
		this.height = height;
	}
	public Vector getNormal(Point3D p) 
	{
		return null;
	}
	
	public double getHeight() 
	{
		return height;
	}
	@Override
	public String toString() 
	{
		return "Cylinder [height=" + height + "]";
	}
}
 */