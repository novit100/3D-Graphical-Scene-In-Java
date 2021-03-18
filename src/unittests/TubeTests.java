package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import static primitives.Util.*;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Nov and Ronni
 *
 */

public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Ray r= new Ray( new Point3D(0,0,0),new Vector(0,1,0));
		Tube t= new Tube(r,1);
		Point3D p = new Point3D(1,0,1);
		Vector n= t.getNormal(p);
		assertTrue("bad normal to tube",isZero(r.getDir().dotProduct(n)));
		// =============== Boundary Values Tests ==================
        // 
        try {
        	new Tube(r,0).getNormal(p);
            fail("GetNormal() should throw an exception, but it failed");
        } catch (Exception e) {}
	}

}
