package unittests;

import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.BasicRayTracer;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class ReflectionRefractionTestMini {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50), 50) //
				.setEmmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().setKd(0.4).setKs(0.3).set_nShininess(100).setKt(0.3)),
				new Sphere(new Point3D(0, 0, -50), 25) //
				.setEmmission(new Color(java.awt.Color.RED)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
				.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene)).setDebugPrint().setMultithreading(3);
		render.renderImage();
		render.writeToImage();
	}

	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000), 400) //
				.setEmmission(new Color(0, 0, 100)) //
				.setMaterial(new Material().setKd(0.25).setKs(0.25).set_nShininess(20).setKt(0.5)),
				new Sphere(new Point3D(-950, -900, -1000), 200) //
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

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirroredIMPROVED", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene).setDistanceForBlurryGlossy(2)).setDebugPrint()
				.setMultithreading(3);

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
				new Sphere(new Point3D(60, 50, -50), 30) //
				.setEmmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene)).setDebugPrint().setMultithreading(3);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a three spheres lighted by a spot light, the red one is
	 * a mirror and the smallest is transparency
	 * 
	 */
	@Test
	public void ourPicture() {

		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //

				.setViewPlaneSize(300, 300).setDistance(400);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.setBackground(new Color(java.awt.Color.BLUE));
		scene.geometries.add(
				// 2 eyes
				// left eye x y depth
				new Sphere(new Point3D(170, 60, -900), 120) // the large white sphere
				.setEmmission(new Color(100, 100, 100)) //
				.setMaterial(new Material().setKd(0).setKs(0.2).set_nShininess(0).setKt(0.6).setKr(0.2)),

				new Sphere(new Point3D(160, 60, -800), 40) // the tiny black sphere
				.setEmmission(new Color(Color.RED)) //
				.setMaterial(new Material().setKd(0).setKs(0.8).set_nShininess(0).setKt(0).setKr(0)),

				// right eye
				new Sphere(new Point3D(-170, 60, -900), 120) // the large white sphere
				.setEmmission(new Color(100, 100, 100)) //
				.setMaterial(new Material().setKd(0).setKs(0.2).set_nShininess(0).setKt(0.6).setKr(0.2)),

				new Sphere(new Point3D(-160, 60, -800), 40) // the tiny black sphere
				.setEmmission(new Color(java.awt.Color.BLACK)) //
				.setMaterial(new Material().setKd(0).setKs(0.8).set_nShininess(0).setKt(0).setKr(0)),

				new Triangle(new Point3D(-50, 60, -800), new Point3D(50, 60, -800), // the triangle

						new Point3D(0, 70, -200)).setEmmission(new Color(java.awt.Color.orange)).setMaterial(
								new Material().setKd(0.25).setKs(0.9).set_nShininess(20).setKt(0).setKr(1)));

		scene.lights.add(new SpotLight(new Color(200, 200, 200), new Point3D(1000, -550, 1000), new Vector(0, -1, 0))
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(
				new PointLight(new Color(300, 500, 500), new Point3D(-50, -50, 50)).setKl(0.00001).setKq(0.000001));

		ImageWriter imageWriter = new ImageWriter("ourPicRefrectionRefractionafterdelta", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracerBase(new BasicRayTracer(scene)).setDebugPrint().setMultithreading(3);

		render.renderImage();
		render.writeToImage();

	}

	// Render render = new Render() //
	// .setImageWriter(imageWriter) //
	// .setCam(camera) //
	// .setRayTracerBase(new
	// BasicRayTracer(scene).setIsimprovement(true).setNumOfRaysGlossy(25).setDistanceForBlurryGlossy(2)).setDebugPrint().setMultithreading(3);

	// Render render = new Render() //
	// .setImageWriter(imageWriter) //
	// .setCam(camera) //
	// .setRayTracerBase(new
	// BasicRayTracer(scene).setIsimprovement(true).setNumOfRaysGlossy(25));

	/**
	 * glossy test
	 */
	@Test
	public void Glass() {

		Scene scene = new Scene("Cube scene");
		Camera camera = (new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, 1, 0)))
				.setDistance(1000).setViewPlaneSize(150, 150);

		scene.setBackground(new Color(25, 25, 112));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.geometries.add(/*
		 * new Polygon( // AEFD
		 * 
		 * new Point3D(0, 0, 0), new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new
		 * Point3D(-50, 0, 50))
		 * 
		 * .setEmmission(new Color(105, 105, 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)), new
		 * Polygon( // TOP new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new
		 * Point3D(0, 70, 100), new Point3D(50, 70, 50)).setEmmission(new Color(105,
		 * 105, 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)), new
		 * Polygon( // DFHB new Point3D(-50, 0, 50), new Point3D(-50, 70, 50), new
		 * Point3D(0, 70, 100), new Point3D(0, 0, 100)).setEmmission(new Color(105, 105,
		 * 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)), new
		 * Polygon( // BHGC new Point3D(0, 0, 100), new Point3D(0, 70, 100), new
		 * Point3D(50, 70, 50), new Point3D(50, 0, 50)).setEmmission(new Color(105, 105,
		 * 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)), new
		 * Polygon( // CGEA new Point3D(50, 0, 50), new Point3D(50, 70, 50), new
		 * Point3D(0, 70, 0), new Point3D(0, 0, 0)) .setEmmission(new Color(105, 105,
		 * 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)), new
		 * Polygon( // BOTTOM new Point3D(0, 0, 0), new Point3D(-50, 0, 50), new
		 * Point3D(0, 0, 100), new Point3D(50, 0, 50)) .setEmmission(new Color(105, 105,
		 * 105)) .setMaterial(new
		 * Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0))
		 * 
		 * ,
		 */
				new Sphere(new Point3D(0, 35, 50), 25).setEmmission(new Color(java.awt.Color.blue))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(100))

				,
				new Plane(new Point3D(0, -5, 0), new Vector(0, 1, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0).set_nShininess(50).setKt(0).setKr(0.8)
						.setRadiusForGlossy(0.08))

				,
				new Sphere(new Point3D(-100, 35, 0), 30).setEmmission(new Color(java.awt.Color.GREEN))
				.setMaterial(new Material().setKd(0.3).setKs(0).set_nShininess(900).setKt(0).setKr(0)
						.setRadiusForGlossy(0.08)),
				new Sphere(new Point3D(100, 35, 0), 30).setEmmission(new Color(java.awt.Color.RED))
				.setMaterial(new Material().setKd(0.3).setKs(0).set_nShininess(900).setKt(0).setKr(0)
						.setRadiusForGlossy(0.08))

				);

		scene.lights.addAll(List.of(
				new SpotLight(new Color(1000, 600, 1000), new Point3D(-100, 100, 100), new Vector(1, -0.4, -1)).setKc(1)
				.setKl(0.0001).setKq(0.00005),
				new DirectionalLight(new Color(255, 215, 0), new Vector(-1, -0.4, 1))));
		int p = 500;
		ImageWriter imageWriter = new ImageWriter("Glass", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCam(camera)
				.setRayTracerBase(new BasicRayTracer(scene).setDistanceForBlurryGlossy(80).setNumOfRaysGlossy(100))
				.setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
		// whitin glossy
		ImageWriter imageWriter1 = new ImageWriter("Glass whitin glossy", p, p);
		Render render1 = new Render().setImageWriter(imageWriter1).setCam(camera)
				.setRayTracerBase(new BasicRayTracer(scene)).setMultithreading(3).setDebugPrint()
				.setSupersampling(true);

		render1.renderImage();
		render1.writeToImage();
	}

	//////////////////////////////////////////////////////// blurry
	//////////////////////////////////////////////////////// //////////////////////
	/**
	 * tank picture (look like siryan tank)
	 */
	@Test
	public void blurryTest() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 10000, 5200), new Vector(0, -1, -0.5), new Vector(0, -0.5, 1))
				.setDistance(13900.13562).setViewPlaneSize(5000, 5000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));
		scene.geometries.add(
				// new Polygon(new Point3D(-500, -500, 500), new Point3D(500, -500, 500), new
				// Point3D(500, -700, 250),
				// new Point3D(-500, -700, 250)).setEmmission(new
				// Color(java.awt.Color.DARK_GRAY))
				/// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// SIDE BACK
				// new Polygon(new Point3D(-500, 500, 500), new Point3D(500, 500, 500), new
				// Point3D(500, 700, 250),
				// new Point3D(-500, 700, 250)).setEmmission(new
				// Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// side front
				// new Polygon(new Point3D(500, -500, 500), new Point3D(500, 500, 500), new
				// Point3D(500, 700, 250),
				// new Point3D(500, -700, 250)).setEmmission(new
				// Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// side RIGHT
				// new Polygon(new Point3D(-500, -500, 500), new Point3D(-500, -700, 250), new
				// Point3D(-500, 700, 250),
				// new Point3D(-500, 500, 500)).setEmmission(new
				// Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// side left
				// new Polygon(new Point3D(-500, -500, 500), new Point3D(500, -500, 500), new
				// Point3D(500, 500, 500),
				// new Point3D(-500, 500, 500)).setEmmission(new
				// Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), // up
				// side

				// new Polygon(new Point3D(-400, 500, 0), new Point3D(-400, 700, 250), new
				// Point3D(-400, -700, 250),
				// new Point3D(-400, -500, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// LEFT SIDE DOWN
				// new Polygon(new Point3D(400, -700, 250), new Point3D(400, 700, 250), new
				// Point3D(400, 500, 0),
				// new Point3D(400, -500, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// RIGTH SIDE DOWN
				// new Polygon(new Point3D(400, 700, 250), new Point3D(-400, 700, 250), new
				// Point3D(-400, 500, 0),
				// new Point3D(400, 500, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// front down
				// new Polygon(new Point3D(-400, -700, 250), new Point3D(400, -700, 250), new
				// Point3D(400, -500, 0),
				// new Point3D(-400, -500, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				// .setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), //
				// back down

				new Triangle(new Point3D(490, -350, 150), new Point3D(490, 0, 150), new Point3D(100, 350, 150)) //
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900))
				.setEmmission(new Color(java.awt.Color.gray)), //

				new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmmission(new Color(100, 100, 100))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(500)), // flor
				new Polygon(new Point3D(300, 2000, 2000), new Point3D(300, 2000, 0), new Point3D(-300, 2000, 0),
						new Point3D(-300, 2000, 2000))
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),
				///////////////////////////////////////////////////////////
				////////////////////////// x y depth
				new Sphere(new Point3D(150, 5000, 2005), 120) // the large white sphere
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material()
						.setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point3D(100, 5000, 2005), 120), // the large white sphere

				new Sphere(new Point3D(100, 5000, 2005), 120) // the large white sphere
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material()
						.setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point3D(100, 50000, 2005), 120), // the large white sphere

				new Sphere(new Point3D(500, 30, 2005), 120) // the large white sphere
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material()
						.setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point3D(100, 60, 2005), 120), // the large white sphere

				new Sphere(new Point3D(-300, 50, 2005), 120) // the large white sphere
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material()
						.setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point3D(100, 60, 2005), 120), // the large white sphere

				new Sphere(new Point3D(50, 50, 2005), 120) // the large white sphere
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material()
						.setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point3D(100, 60, 2005), 120), // the large white sphere

				new Triangle(new Point3D(490, -350, 150), new Point3D(-1000, -1000, -1000), new Point3D(100, 350, 150)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),
				new Sphere(new Point3D(100, 60, 2005), 120), //
				/////////////
				new Triangle(new Point3D(0, 0, 0), new Point3D(-1000, -1000, -1000),
						new Point3D(100, 350, 150)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),
				new Sphere(new Point3D(100, 60, 2005), 120), //
				//////////////
				/////////////
				//

				new Sphere(new Point3D(-1000, 30, 2005), 150) // the large white sphere
				.setEmmission(new Color(java.awt.Color.red))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), // the large white
				// sphere
				//////////////
				new Polygon(new Point3D(300, 2000, 2000), new Point3D(300, 2000, 0), new Point3D(-300, 2000, 0),
						new Point3D(-300, 2000, 2000)),

				// **********************************//
				/////////////////////////	x y depth
				new Triangle(new Point3D(300, 2000, 2000), 
						new Point3D(300, 2000, 0),
						new Point3D(-300, 2000, 0)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),

				new Triangle(new Point3D(-300, 2000, 0), new Point3D(-300, 2000, 2000),
						new Point3D(300, 2000, 2000)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),
				new Sphere(new Point3D(100, 60, 2005), 120),
				new Sphere(new Point3D(0, 0, 500), 350).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(900)), // the chriach

				new Polygon(new Point3D(300, 2000, 2000), new Point3D(300, 2000, 0), new Point3D(-300, 2000, 0),
						new Point3D(-300, 2000, 2000)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2))

				// **********************************//
				);

		scene.lights.addAll(List.of(new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				// ((SpotLight) new SpotLight(new Color(400, 240, 500), new Point3D(-500, -1000,
				// 3000),
				// new Point3D(0, 0, 700)) //
				// .setKl(1E-5).setKq(1.5E-7)),.setEmission(new Color(java.awt.Color.DARK_GRAY))

				new SpotLight(new Color(30, 1000, 1000), new Point3D(0, 1020, 700), new Vector(0, -1, 0)) //
				.setKl(1E-5).setKq(1.5E-7)));
		int p = 700;
		ImageWriter imageWriter = new ImageWriter("whitout blurry ", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCam(cam).setRayTracerBase(new BasicRayTracer(scene))
				.setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
		render = render.setImageWriter(new ImageWriter("whit blurry", p, p))
				.setRayTracerBase(new BasicRayTracer(scene).setNumOfRaysBlurry(100).setDistanceForBlurryGlossy(80))
				.setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * 
	 */
	@Test
	public void blurryTest1() {
		Scene scene = new Scene("test case");
		Camera cam = new Camera(new Point3D(0, 0, 1), new Vector(0, 1, 0), new Vector(1, 0, 0))
				.setDistance(14000).setViewPlaneSize(5000, 5000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.RED, 0.15));
		scene.geometries.add(
				new Triangle(
						new Point3D(300, 2000, 2000), 
						new Point3D(300, 2000, 0),
						new Point3D(-300, 2000, 0)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)
						.setRadiusForBlurry(2)),
				
				new Sphere(new Point3D(0, 100000, 0), 6000).setEmmission(new Color(java.awt.Color.CYAN))
				.setMaterial(new Material().setKd(0.9).setKs(0.5).set_nShininess(900)),

				new Triangle(new Point3D(-300, 2000, 0), new Point3D(-300, 2000, 2000),
						new Point3D(300, 2000, 2000)) //
				.setEmmission(new Color(java.awt.Color.DARK_GRAY))
				.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0))
				);
		scene.lights.addAll(List.of(new DirectionalLight(new Color(50, 50, 0), new Vector(0, -1, -500)),
				new SpotLight(new Color(10, 10, 10), new Point3D(0, 1, 0), new Vector(0, 1, 0)) //
				.setKl(1E-5).setKq(1.5E-7)));
		int p = 700;
		ImageWriter imageWriter = new ImageWriter("whitout blurry1 ", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCam(cam).setRayTracerBase(new BasicRayTracer(scene))
				.setMultithreading(3).setDebugPrint();

		
		
		render.renderImage();
		render.writeToImage();
		render = render.setImageWriter(new ImageWriter("whit blurry1", p, p))
				.setRayTracerBase(new BasicRayTracer(scene).setNumOfRaysBlurry(100).setDistanceForBlurryGlossy(80))
				.setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}
}
