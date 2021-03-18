package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Nov and Ronni
 *
 */

public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p= new Point3D(1, 1, 6);
		Point3D p1=new Point3D(1,1,1);
		Sphere s = new Sphere(p1,5);
		Vector v= p1.subtract(p).normalize();
		
		assertEquals("Bad normal to sphere",v,s.getNormal(p));
		// =============== Boundary Values Tests ==================
        // 
		try {
        	new Sphere(p1,0).getNormal(p);
            fail("GetNormal() should throw an exception, but it failed");
        } catch (Exception e) {}
	}

}
