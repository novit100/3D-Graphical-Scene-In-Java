package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon{
	//////// ctor /////////////////
	public Triangle(Point3D p1,Point3D p2,Point3D p3) {
		super(p1,p2,p3);
	}
	////// get  ///////////////////
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
   
	/////////// intersections ///////
	/**
	 * @param ray
	 * @return a list of GeoPoints- intersections of the ray with the triangle, and this triangle
	 */
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray)
	{

		List<GeoPoint> intersections = plane.findGeoIntersections(ray);//find intersections with the plane
		//(triangle extends polygon and polygon contains plane)
		if (intersections == null) return null;//if no intersections with plane

		Point3D p0 = ray.getP0();
		Vector v = ray.getDir();
		
		
		//we are creating a kind of pyramid by 3 vectors 
		Vector v1 = vertices.get(0).subtract(p0).normalized();
		Vector v2 = vertices.get(1).subtract(p0).normalized();
		Vector v3 = vertices.get(2).subtract(p0).normalized();
     
		//if the ray lays on the 'Pea'
		//so the intersection is on the edge of the triangle therefore we don't count it as an intersection 
		double s1 = v.dotProduct(v1.crossProduct(v2));//[v1.crossProduct(v2)=normal of v1 and v2
		if (isZero(s1)) return null;
		double s2 = v.dotProduct(v2.crossProduct(v3));//[v2.crossProduct(v3)=normal of v2 and v3
		if (isZero(s2)) return null;
		double s3 = v.dotProduct(v3.crossProduct(v1));//[v3.crossProduct(v1)=normal of v3 and v1
		if (isZero(s3)) return null;

		if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))//the point is inside the triangle so the ray intersects the triangle 
		{
			for (GeoPoint geo : intersections) //for each geoPoint in intersections change the geometry to be 'this'
            {
                geo.geometry = this;//triangle and not plane
            }
			return intersections;//return the intersection
		}
		else
			return null;//the ray is on the plane but outside the triangle
	}
	 ////// admin //////////////////
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
