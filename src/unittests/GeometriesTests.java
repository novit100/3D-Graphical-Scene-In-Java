package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import geometries.Geometries;
import geometries.*;
import primitives.*;

/**
 * 
 * @author
 *
 */

public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() 
	{//beware that a ray can cut a sphere twice and the plane one time (in this case the triangle is not cut).
		Geometries geo=new Geometries();//an empty geometry array list 
		//we implemented findIntersections of sphere, triangle and plane.
		Geometries ourGeos=new Geometries(new Sphere(new Point3D(4,0,0),5),new Triangle(new Point3D(1,4,0),new Point3D(1,2,0),new Point3D(5,2,0)),new Plane(new Point3D(1,2,0),new Point3D(0,7,0),new Point3D(1,0,0)));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Some (but not all) geometries are cut  
		List<Point3D> resultList = ourGeos.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(2,-4,-4)));
		assertEquals("Wrong number of points", 3, resultList.size());
		// =============== Boundary Values Tests ==================
		//TC02:Empty geometries collection
		assertEquals("Wrong number of points", null,geo.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(2,-4,-4))));
		//TC03:No shape is cut
		assertEquals("Wrong number of points", null,ourGeos.findIntersections(new Ray(new Point3D(0, 0, 7),
				new Vector(1,0,0))));
		//TC04:One shape is cut
		resultList = ourGeos.findIntersections(new Ray(new Point3D(0, 0, 10),
				new Vector(-8,1,-10)));
		assertEquals("Wrong number of points",1 , resultList.size());
		//TC05:All shapes are cut (the sphere 2 times and the plane and triangle one time each)
		resultList = ourGeos.findIntersections(new Ray(new Point3D(2, 5, 4),
				new Vector(0,-2,-4)));
		assertEquals("Wrong number of points",4 , resultList.size());
	}

}
