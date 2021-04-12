package elements;

import primitives.*;
import static primitives.Util.*;
public class Camera {
	//========== fields ==============//
private Point3D p0; //location of the camera
private Vector vUp;//upwards direction vector 
private Vector vTo;//direction vector towards the object
private Vector vRight;//presents the horizontal ("ofki") direction of the camera
private double width;//the width of the view plane
private double height;//the height of the view plane
private double distance;
//===============ctor============//


public Camera(Point3D location, Vector to, Vector up) {
	
	this.p0 = location;
	this.vTo = to.normalized();//we want a normalized vector in vTo
	this.vUp = up.normalized();//we want a normalized vector in vUp
	this.vRight=(vUp.crossProduct(vTo)).normalized();//a cross product between vUp and vTo and normalizing the vector we got
	if(!isZero(vUp.dotProduct(vTo))) 
		throw new ArithmeticException("the vectors up and to are not orthogonal");
		
}
   //============ get ==============//
public Point3D getLocation() {
	return p0;
}
public Vector getTo() {
	return vTo;
}
public Vector getUp() {
	return vUp;
}
public Vector getRight() {
	return vRight;
}
  //============= set ==============//
/**
 * this method we get two elements: the width and height and after updating the size of the View Plane we return the camera itself.
 * by doing so we implement the design pattern "Builder",which is very useful 
 * @param width
 * @param height
 * @return camera 
 */
public Camera setViewPlaneSize(double width_, double height_) {
	this.width=width_;
	this.height=height_;
	return this;
}
/**
 * this method we get the distance and after updating the distance of the camera from the view plane we return the camera itself.
 * by doing so we implement the design pattern "Builder",which is very useful 
 * @param distance
 * @return camera
 */
public Camera setDistance(double distance_) {
	this.distance=distance_;
	return this;
}
//============= functions ==============//
/**
 * 
 * @param nX
 * @param nY
 * @param j
 * @param i
 * @return
 */
public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
	
	return null;
}

}
