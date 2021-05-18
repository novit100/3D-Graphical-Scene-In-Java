package unittests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * 
 * @author Ronni & Nov
 *
 */

public class RayTests {
	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
@Test
public void testFindClosestPoint(){
	//restart geometries
	Geometries geo=new Geometries(new Plane(new Point3D(0,1,1),new Point3D(1,0,1),new Point3D(0,0,1)),
			new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0)),
			new Plane(new Point3D(0,1,2),new Point3D(1,0,2),new Point3D(0,0,2)));
	// ============ Equivalence Partitions Tests ============== //
	// TC01: test of finding the closest point that is in the middle of the arrayList
	Ray r=new Ray(new Point3D(5, 5, 5.1),new Vector(1,1,1));
	List<Point3D> lst_points;
	lst_points=new ArrayList<Point3D>();
    for(int i=0;i<10;i++) {
    lst_points.add(i,new Point3D(i+2,i+2,i+2));
    }
    assertEquals("did not find the right closest point to the ray -in the middle",new Point3D(5,5,5) , r.findClosestPoint(lst_points));
    
    // =============== Boundary Values Tests ================== //
    //TC02:test of empty list case-the method should return null
    List<Point3D> lst_NoPoints;
    lst_NoPoints=new ArrayList<Point3D>();
    assertEquals("did not return null when the array list was empty", null, r.findClosestPoint(lst_NoPoints));
    
    //TC03:test of finding the closest point that is in the beginning of the arrayList
    Ray r3=new Ray(new Point3D(2, 2, 2.1),new Vector(1,1,1));
    assertEquals("did not find the right closest point to the ray -in the begining of the list",new Point3D(2,2,2) , r3.findClosestPoint(lst_points));
    
    //TC04:test of finding the closest point that is in the end of the arrayList
    Ray r4=new Ray(new Point3D(11, 11, 11.1),new Vector(1,1,1));
    assertEquals("did not find the right closest point to the ray -in the end of the list",new Point3D(11,11,11) , r4.findClosestPoint(lst_points));
}

/**
 * Test method for {@link primitives.Ray#getClosestGeoPoint(java.util.List)}.
 */
@Test
public void testFindClosestGeoPoint() 
{
	//restart geometries
	Geometries geo=new Geometries(new Plane(new Point3D(0,1,1),new Point3D(1,0,1),new Point3D(0,0,1)),
			new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0)),
			new Plane(new Point3D(0,1,2),new Point3D(1,0,2),new Point3D(0,0,2)));
	//plane1= parallel to [xy]: z=1, 
	//plane2= [xy]: z=0,  
	//plane3= parallel to [xy]: z=2
	
	// ============ Equivalence Partitions Tests ==============
	// TC01: the middle point is the closest
	Ray ray=new Ray(new Point3D(0, 0, -0.5),new Vector(0,0,1));
	List<GeoPoint> result=geo.findGeoIntersections(ray);
	GeoPoint closePointAndGeo =ray.findClosestGeoPoint(result);
	assertEquals("Wrong close intersection",result.get(1), closePointAndGeo);
	
	// =============== Boundary Values Tests ================== 
	// TC02: empty list
	ray=new Ray(new Point3D(0, 0, -5),new Vector(0,0,-1));
	result=geo.findGeoIntersections(ray);
	closePointAndGeo =ray.findClosestGeoPoint(result);
	assertEquals("Wrong close intersection", null, closePointAndGeo);
	
	// TC03: the first point is the closest
	ray=new Ray(new Point3D(0, 0, 1.5),new Vector(0,0,-1));
	result=geo.findGeoIntersections(ray);
	closePointAndGeo =ray.findClosestGeoPoint(result);
	assertEquals("Wrong close intersection", result.get(0), closePointAndGeo);
	
	// TC04: the last point is the closest
	ray=new Ray(new Point3D(0, 0, 2.5),new Vector(0,0,-1));
	result=geo.findGeoIntersections(ray);
	closePointAndGeo =ray.findClosestGeoPoint(result);
	assertEquals("Wrong close intersection", result.get(2), closePointAndGeo);
}

}


