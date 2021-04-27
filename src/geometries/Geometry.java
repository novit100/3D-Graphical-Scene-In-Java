package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable{
protected Color emmission =new Color(Color.BLACK);

//////////////////get ///////////////////////
/**
 * a getter function that returns the emmission color
 * @return Color
 */
public Color getEmmission() {
	return emmission;
}
public abstract Vector getNormal(Point3D p);
/////////////// set //////////////////////////
/**
 * a setter function that updates the emmission field and returns the 
 * Geometry itself -for the usage of Builder design pattern
 * @param emmission
 */
public Geometry setEmmission(Color emmission) {
	this.emmission = emmission;
	return this;
}


}
