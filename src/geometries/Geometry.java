package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry extends Intersectable{
protected Color emmission =new Color(Color.BLACK);
private Material material=new Material();//the material of the geometry- with special features.

//////////////////get ///////////////////////
/**
 * a getter function that returns the emmission color
 * @return Color
 */
public Color getEmmission() {
	return emmission;
}
/**
 * @return the material
 */
public Material getMaterial()
{
	return material;
}
/**
 * @param point
 * @return the normal vector
 */
public abstract Vector getNormal(Point3D p);
/////////////// set //////////////////////////

/**
 * set material and return the object itself
 * @param emission
 */
public Geometry setMaterial(Material material) {
	this.material = material;
	return this;
	
}

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
