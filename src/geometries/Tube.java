package geometries;
import primitives.*;

public class Tube 
{
Ray axisRay;
double radius;
///////// ctor  //////////
public Tube(Ray axisRay, double radius) 
{
	super();
	this.axisRay = axisRay;
	this.radius = radius;
}
///////// get ////////////
/**
 * @param p
 * @return the normal
 */
public Vector getNormal(Point3D p) {
	//get ray point and vector
    Point3D rayP = axisRay.getP0();
    Vector rayV = axisRay.getDir();

    //get point on the same level as the given point
    double t = rayV.dotProduct(p.subtract(rayP));

    //if the point is not on the same level then get the point
    //and return the normal
    if(!Util.isZero(t)){
        Point3D o = rayP.add(rayV.scale(t));
        return p.subtract(o).normalized();
    }

    //if the point is on the same level then return normal
    return p.subtract(axisRay.getP0()).normalized();

}

public Ray getAxisRay() 
{
	return axisRay;
}

public double getRadius() 
{
	return radius;
}
//////////// admin ///////////
@Override
public String toString() 
{
	return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
}




}