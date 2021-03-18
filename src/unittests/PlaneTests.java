package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import static primitives.Util.*;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Ronni and Nov
 *
 */

public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p1= new Point3D(1,2,0);
		Point3D p2= new Point3D(-4,7,0);
		Point3D p3= new Point3D(1,0,5);
		Plane p= new Plane(p1,p2, p3);
		Vector v1=p1.subtract(p2);
		Vector v2=p2.subtract(p3);
		Vector v3=p3.subtract(p1);
		Vector n=p.getNormal(p1);
		assertTrue("ERROR: Bad normal to plane", isZero(v1.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v2.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v3.dotProduct(n)));
		 try {
	        	new Plane(new Point3D(1,2,3),new Point3D(2,4,6),new Point3D(4,8,12)).getNormal(p1);
	            fail("GetNormal() should throw an exception, but it failed");
	        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p1= new Point3D(1,2,0);
		Point3D p2= new Point3D(-4,7,0);
		Point3D p3= new Point3D(1,0,5);
		Plane p= new Plane(p1,p2, p3);
		Vector v1=p1.subtract(p2);
		Vector v2=p2.subtract(p3);
		Vector v3=p3.subtract(p1); 
		Vector n=p.getNormal(p1);
		assertTrue("ERROR: Bad normal to plane", isZero(v1.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v2.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v3.dotProduct(n)));
		 try {
	        	new Plane(new Point3D(1,2,3),new Point3D(2,4,6),new Point3D(4,8,12)).getNormal();
	            fail("GetNormal() should throw an exception, but it failed");
	        } catch (Exception e) {}
	}

}
