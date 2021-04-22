package unittests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
}
