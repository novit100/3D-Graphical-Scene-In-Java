package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import static primitives.Util.*;
import primitives.*;
import geometries.*;

/**
 * 
 *  @author Nov and Ronni
 *
 */

public class PlaneTests 
{  
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
		assertTrue("ERROR: Bad normal to plane", isZero(v1.dotProduct(n)));//if the dot product== 0, it really the normal to the plane
		assertTrue("ERROR: Bad normal to plane", isZero(v2.dotProduct(n)));//if the dot product== 0, it really the normal to the plane
		assertTrue("ERROR: Bad normal to plane", isZero(v3.dotProduct(n)));//if the dot product== 0, it really the normal to the plane
		try {
			new Plane(new Point3D(1,2,3),new Point3D(2,4,6),new Point3D(4,8,12)).getNormal(p1);//a case that all the points are on the same vector- cannot create the plane
			fail("GetNormal() should throw an exception, but it failed");
		} catch (Exception e) {}
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:
		Point3D p1= new Point3D(1,2,0);
		Point3D p2= new Point3D(-4,7,0);
		Point3D p3= new Point3D(1,0,5);
		Plane p= new Plane(p1,p2, p3);
		Vector v1=p1.subtract(p2);
		Vector v2=p2.subtract(p3);
		Vector v3=p3.subtract(p1); 
		Vector n=p.getNormal(p1);
		//if the dot product !=0 so the normal is not correct
		assertTrue("ERROR: Bad normal to plane", isZero(v1.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v2.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v3.dotProduct(n)));
		try {
			new Plane(new Point3D(1,2,3),new Point3D(2,4,6),new Point3D(4,8,12)).getNormal();
			fail("GetNormal() should throw an exception, but it failed");
		} catch (Exception e) {}
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}
	 */
	@Test
	public void findIntersections() {
		try {
			Plane pl = new Plane(new Point3D(0, 0, -3), new Vector(0, 0, -1));
			Ray r;
			  // ============ Equivalence Partitions Tests ==============
			// TC01: The Ray must be neither orthogonal nor parallel to the plane
			// the ray intersects the plane
			r = new Ray(new Point3D(1, 1, 0), new Vector(2, 1, -1));
			List<Point3D> result = pl.findIntersections(r);
			assertEquals("uncorrect intersection! the ray intersects the plane", List.of(new Point3D(7, 4, -3)), result);

			//TC02: the ray does not intersect the plane
			r = new Ray(new Point3D(1, 1, 0), new Vector(2, 1, 1));
			result=pl.findIntersections(r);
			assertEquals("the ray does not intersect the plane", null, result);

			
	        // =============== Boundary Values Tests ==================
			//TC03: Ray is parallel to the plane
			// the ray is included in the plane
			r = new Ray(new Point3D(1, 2, -3), new Vector(2, 1, 0));
			assertEquals("incorrect intersection! the ray is parallel and included in the plane", null, pl.findIntersections(r));
			//TC04: the ray is not included in the plane
			r = new Ray(new Point3D(1, 2, -2), new Vector(2, 1, 0));
			assertEquals("incorrect intersection! the ray is parallel and not included in the plane", null, pl.findIntersections(r));

			// TC05: Ray is orthogonal to the plane
			// Ray starts before the plane
			r = new Ray(new Point3D(1, 1, 0), new Vector(0, 0, -1));
			assertEquals("incorrect intersection! the ray is orthogonal and starts before the plane",List.of(new Point3D(1, 1, -3)), pl.findIntersections(r));
			//TC06: Ray starts in the plane
			r = new Ray(new Point3D(1, 1, -3), new Vector(0, 0, -1));
			assertEquals("incorrect intersection! the ray is orthogonal and starts in the plane", null, pl.findIntersections(r));
			//TC07: Ray starts after the plane
			r = new Ray(new Point3D(1, 1, -4), new Vector(0, 0, -1));
			assertEquals("incorrect intersection! the ray is orthogonal and starts after the plane", null, pl.findIntersections(r));


			// TC08: Starting point of the ray is on the plane, but the vector is not included in the plane
			r = new Ray(new Point3D(1, 1, -3), new Vector(2, 1, -1));
			assertEquals("incorrect intersection! Starting point of the ray is on the plane, but the vector is not included in the plane", null, pl.findIntersections(r));


			// TC09: Starting point of the ray is equal to the point represents the plane- q0
			r = new Ray(new Point3D(0, 0, -3), new Vector(2, 1, -1));
			assertEquals("incorrect intersection! Starting point of the ray is equal to the point represents the plane- q0", null, pl.findIntersections(r));
	
		}
		catch(IllegalArgumentException e) {}
	}

}
