package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import primitives.*;
import geometries.*;
/**
 * this class is meant to test that the "findIntersection" functions of 
 * sphere,triangle and plane work well with the function "constructRayThroughPixel"
 * @author Ronni & Nov
 */
public class CameraIntegrationTests {
	/**
	 * Point3D p0=Point3D.ZERO;//the camera is at point (0,0,0)
		Vector vTo=new Vector(-1,0,0);
		Vector vUp=new Vector(0,1,0);
		Camera cam=new Camera(p0, vTo, vUp);
		Point3D center=new Point3D(0, 0, -3);
		Sphere spherush=new Sphere(center, 1);

	 */
	Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));//p0=position of camera=(0,0,0)
	Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));//another camera, different position
	//all tests will be on view plane of 3x3:
	int Nx =3;//number of pixels on tzir x of view plane
	int Ny =3;//number of pixels on tzir y of view plane
	
	/**
	 * help function- to implement the principle of DRY=dont repeat yourself, 
	 * we added a help func that receives an intersectable geometry, and the wanted camera,
	 * calculates the number of intersections and returns it.
	 * @param geometry- (intersectable, that implements the method "findIntersections"
	 * @param cam
	 * @return
	 */
	private int countIntersec_contructRay(Intersectable geometry, Camera cam)
	{
		List<Point3D> res;//intersections list
		int count = 0;
		for (int i = 0; i < Nx; ++i) //for each index of width
		{
			for (int j = 0; j < Ny; ++j) //for each index of height
			{
				Ray ray = cam.setViewPlaneSize(3,3).setDistance(1).constructRayThroughPixel(Nx,Ny,j,i);//3x3. create ray through the pixel
				res = geometry.findIntersections(ray);//calculate all intersections of a ray and the geometry
				if (res != null)					  //if there are intersections of the specific ray with the geometry
					count += res.size();			  //add the number of intersections to the general counter of intersections with the specific geometry.
			}
		}
		return count;//return the number of intersections of all rays with this geometry.
	}

	/////////// triangle ///////////////
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
	 */
	@Test
	public void constructRayThroughPixel_Triangle() 
	{
		//TC 01: small triangle centered in front of camera- one intersection through the middle ray.
		Triangle tr1 =  new Triangle( new Point3D(-1, 1, 2),new Point3D(1, 1, 2),new Point3D(0, -1, 2));
		int count = countIntersec_contructRay(tr1,cam2);
		assertEquals("not good intersection sum",1,count);
		System.out.println("count: "+count);
		
		//TC 02: higher, narrow triangle, placed in front of 2 centered- upper pixels. 2 intersections.
		Triangle tr2 =  new Triangle( new Point3D(-1, 1, 2),new Point3D(1, 1, 2),new Point3D(0, -20, 2));
		count = countIntersec_contructRay(tr2,cam2);
		assertEquals("not good intersection sum",2,count);
		System.out.println("count: "+count);
	}
	
	/////////////////// sphere/////////////////
	
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}
	 */
	@Test 
	public void constructRayThroughPixel_Sphere() 
	{
		//TC 01: radius=1, 2 intersections from the middle ray
		Sphere sph1 =  new Sphere( new Point3D(0, 0, -3),1);
		int count = countIntersec_contructRay(sph1,cam1);
		assertEquals("not good intersection sum",2,count);
		System.out.println("count: "+count);

		//TC 02: radius=2.5, 2 intersections from each ray- total=18
		Sphere sph2 =  new Sphere( new Point3D(0, 0, 2.5),2.5);
		count = countIntersec_contructRay(sph2,cam2);
		assertEquals("not good intersection sum",18,count);
		System.out.println("count: "+count);

		//TC 03: radius=2, 2 intersections from all 5 centered rays #, total=10
		Sphere sph3 =  new Sphere( new Point3D(0, 0, 2),2);
		count = countIntersec_contructRay(sph3,cam2);
		assertEquals("not good intersection sum",10,count);
		System.out.println("count: "+count);

		//TC 04: radius=4, camera is inside the sphere, 1 intersection from each ray (one side), total- 9
		Sphere sph4 =  new Sphere( new Point3D(0, 0, 1),4);
		count = countIntersec_contructRay(sph4,cam2);
		assertEquals("not good intersection sum",9,count);
		System.out.println("count: "+count);

		//TC 05: radius=0.5, sphere behind the camera, no intersections.
		Sphere sph5 =  new Sphere( new Point3D(0, 0, -1),0.5);
		count = countIntersec_contructRay(sph5,cam2);
		assertEquals("not good intersection sum",0,count);
		System.out.println("count: "+count);
	}

	///////////////// plane///////////////////////
	/**
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}
	 */
	@Test
	public void constructRayThroughPixel_Plane()
	{
		//TC 01: plane orthogonal to vTo, (direction vector towards the object from the camera)
		//supposed to be all together 9 intersections
		
		Plane plane1 =  new Plane( new Point3D(0, 0, 7),new Vector(0,0,1));
		int raySum = countIntersec_contructRay(plane1,cam2);
		assertEquals("not good intersection sum",9,raySum);
		System.out.println("ray Sum: "+raySum);

		//TC 02: plane is tilted but still all rays intersect it.  
		//also supposed to be 9 intersections
		Plane plane2 =  new Plane( new Point3D(0, 0, 2),new Vector(0,1,3));
		raySum = countIntersec_contructRay(plane2,cam2);
		assertEquals("not good intersection sum",9,raySum);
		System.out.println("ray Sum: "+raySum);

		//TC 03: plane is very tilted and the rays through the bottom row of pixels- can't reach the plane.
		//supposed to be 6 intersection.
		Plane plane3 =  new Plane( new Point3D(0, 0, 2),new Vector(0,3,1));
		raySum = countIntersec_contructRay(plane3,cam2);
		assertEquals("not good intersection sum",6,raySum);
		System.out.println("intersection Sum: "+raySum);
	}
}
