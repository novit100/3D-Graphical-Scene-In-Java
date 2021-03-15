package geometries;

import primitives.Point3D;

public class Triangle extends Polygon{
public Triangle(Point3D p1,Point3D p2,Point3D p3) {
	super(p1,p2,p3);
}
public Point3D getPoint1() 
{
    return super.vertices.get(0);
}
public Point3D getPoint2() 
{
    return super.vertices.get(1);
}
public Point3D getPoint3() 
{
    return super.vertices.get(2);
}

@Override
public String toString() 
{
    return "Tri{" +
            "P1=" + getPoint1() +
            ", P2=" + getPoint2() +
            ", P3=" + getPoint3() +
            '}' ;
}
	
}
