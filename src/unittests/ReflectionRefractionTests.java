package unittests;

import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50),50) //
				.setEmmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().setKd(0.4).setKs(0.3).set_nShininess(100).setKt(0.3)),
				new Sphere(new Point3D(0, 0, -50),25) //
				.setEmmission(new Color(java.awt.Color.RED)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
				.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000),400) //
				.setEmmission(new Color(0, 0, 100)) //
				.setMaterial(new Material().setKd(0.25).setKs(0.25).set_nShininess(20).setKt(0.5)),
				new Sphere(new Point3D(-950, -900, -1000),200) //
				.setEmmission(new Color(100, 20, 20)) //
				.setMaterial(new Material().setKd(0.25).setKs(0.25).set_nShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
				.setEmmission(new Color(20, 20, 20)) //
				.setMaterial(new Material().setKr(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
				.setEmmission(new Color(20, 20, 20)) //
				.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(60)), //
				new Sphere(new Point3D(60, 50, -50),30) //
				.setEmmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a three spheres lighted by a spot light, the red one is a mirror and the smallest is transparency
	 *  
	 */
	@Test
	public void ourPicture() {
		
		 Camera camera = new Camera(new Point3D(0, 0,0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
		
				.setViewPlaneSize(300, 300).setDistance(400);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.setBackground(primitives.Color.RED);
		scene.geometries.add( //
				new Sphere(new Point3D(0,50,-800),120) //the green sphere 
				.setEmmission(new Color(java.awt.Color.green)) //
				.setMaterial(new Material().setKd(0.6).setKs(0.9).set_nShininess(10000).setKt(0).setKr(1)),
			
				new Sphere(new Point3D(170,110,-900),300) //the large white sphere
				.setEmmission(new Color(100,100,100)) //
				.setMaterial(new Material().setKd(0).setKs(0.2).set_nShininess(1000).setKt(0.6).setKr(0.2)),
				
				new Sphere(new Point3D(-100,30,-790),30) //the tiny blue sphere 
				.setEmmission(new Color(java.awt.Color.blue)) //
				.setMaterial(new Material().setKd(0).setKs(0).set_nShininess(0).setKt(0).setKr(0)),
				//
				new Sphere(new Point3D(0,50,-800),120) //the green sphere
				.setEmmission(new Color(java.awt.Color.green)) //
				.setMaterial(new Material().setKd(0.6).setKs(0.9).set_nShininess(10000).setKt(0).setKr(1)),
			
				new Sphere(new Point3D(170,-50,-900),300) //the large white sphere
				.setEmmission(new Color(100,100,100)) //
				.setMaterial(new Material().setKd(0).setKs(0.2).set_nShininess(1000).setKt(0.6).setKr(0.2)),
				
				
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, 1500),//the triangle
				
				new Point3D(0, -1400, -2000)).setEmmission(new Color(java.awt.Color.orange)).setMaterial(new Material().setKd(0.25).setKs(0.9).set_nShininess(20).setKt(0).setKr(1))); 


		scene.lights.add(new SpotLight(new Color(200,200,200), new Point3D(1000,-550,1000), new Vector(0,-1,0)) 
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new PointLight(new Color(300, 500, 500), new Point3D(-50, -50, 50))
				.setKl(0.00001).setKq(0.000001));
		
		ImageWriter imageWriter = new ImageWriter("ourPicRefrectionRefractionafterdelta", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene));

		render.renderImage();
		render.writeToImage();
		
	}
}
