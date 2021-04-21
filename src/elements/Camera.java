package elements;

import primitives.*;
import static primitives.Util.*;
public class Camera {
	//========== fields ==============//
	//camera
private Point3D p0; //location of the camera
private Vector vUp;//upwards direction vector -towards the camera
private Vector vTo;//direction vector towards the object from the camera
private Vector vRight;//presents the horizontal ("ofki") direction of the camera (for the right)

   //view plane
private double width;//the width of the view plane
private double height;//the height of the view plane
private double distance;//the distance of the camera from the view plane
//===============ctor============//
/**
 * A new Camera. vUp and vTo must be orthogonal
 * @param location- camera location
 * @param up- the upwards vector
 * @param to- the forward vector
 */
public Camera(Point3D location, Vector to, Vector up) {
	if(!isZero(up.dotProduct(to))) 
		throw new ArithmeticException("the vectors up and to are not orthogonal");
	this.p0 = new Point3D(location.getX(),location.getY(),location.getZ());//location of camera
	this.vTo = to.normalized();//we want a normalized vector in vTo
	this.vUp = up.normalized();//we want a normalized vector in vUp
	this.vRight=(vTo.crossProduct(vUp)).normalized();//a cross product between vUp and vTo and normalizing the vector we got
	
		
}
   //============ get ==============//
/** 
 * @return the location of the camera (point 3d)
 */
public Point3D getLocation() {
	return p0;
}
/**
 * @return the vector from the camera towards the object
 */
public Vector getTo() {
	return vTo;
}
/**
 * @return the vector upwards-towards the camera
 */
public Vector getUp() {
	return vUp;
}
/**
 * @return the horizontal vector,from the right of the camera
 */
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
	
	if (isZero(distance)) //the distance between the VP and the camera can't be 0
		throw new IllegalArgumentException("the distance between the VP and the camera can't be 0");
	Point3D centerOfVP= p0.add(vTo.scale(distance));//a point that presents the center of the VP that is straight forward to the camera.
	double heightY =height / nY;// y value on VP
	double widthX = width / nX;	// x value on VP

	double yi = ((i - nY / 2d) * heightY + heightY / 2d);//calculates the y value of the wanted pixel
	double xj = ((j - nX / 2d) * widthX + widthX / 2d);//calculates the x value of the wanted pixel

	Point3D Pij = centerOfVP;// Pij starts to march the VP from the center
	//we need to move Pij ONLY if xj/yi isn't zero   
	if (!isZero(xj)) 
		Pij = Pij.add(vRight.scale(xj)); //Pij to the center of the wanted pixel
	if (!isZero(yi)) 
		Pij = Pij.add(vUp.scale(-yi));  //  Pij to the center of the wanted pixel

	Vector Vij = Pij.subtract(p0);//Vij =  vector from the camera to Pij
	return new Ray(p0, Vij);//create the ray and return it
}


}


       // vRight (x) or vUp- y.  