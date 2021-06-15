package unittests;

import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Polygon;
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
	 * glussy test
	 */
	@Test
	public void GlassCube() {

		Scene scene = new Scene("Cube scene");
		Camera camera = (new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, 1, 0)))
				.setDistance(1000).setViewPlaneSize(150, 150);

		scene.setBackground(new Color(25, 25, 112));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.geometries.add(new Polygon( // AEFD
				new Point3D(0, 0, 0), new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new Point3D(-50, 0, 50))
						.setEmmission(new Color(105, 105, 105))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Polygon( // TOP
						new Point3D(0, 70, 0), new Point3D(-50, 70, 50), new Point3D(0, 70, 100),
						new Point3D(50, 70, 50)).setEmmission(new Color(105, 105, 105))
								.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Polygon( // DFHB
						new Point3D(-50, 0, 50), new Point3D(-50, 70, 50), new Point3D(0, 70, 100),
						new Point3D(0, 0, 100)).setEmmission(new Color(105, 105, 105))
								.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Polygon( // BHGC
						new Point3D(0, 0, 100), new Point3D(0, 70, 100), new Point3D(50, 70, 50),
						new Point3D(50, 0, 50)).setEmmission(new Color(105, 105, 105))
								.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Polygon( // CGEA
						new Point3D(50, 0, 50), new Point3D(50, 70, 50), new Point3D(0, 70, 0), new Point3D(0, 0, 0))
								.setEmmission(new Color(105, 105, 105))
								.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0)),
				new Polygon( // BOTTOM
						new Point3D(0, 0, 0), new Point3D(-50, 0, 50), new Point3D(0, 0, 100), new Point3D(50, 0, 50))
								.setEmmission(new Color(105, 105, 105))
								.setMaterial(new Material().setKd(0.2).setKs(0.2).set_nShininess(30).setKt(0.6).setKr(0))

				, new Sphere(new Point3D(0, 35, 50), 25).setEmmission(new Color(java.awt.Color.red))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).set_nShininess(100))

				,
				new Plane(new Point3D(0, -5, 0), new Vector(0, 1, 0)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
						.setMaterial(new Material().setKd(0.2).setKs(0).set_nShininess(50).setKt(0).setKr(0.8)
								.setRadiusForGlossy(0.08))

				,
				new Sphere(new Point3D(-100, 35, 0), 30).setEmmission(new Color(255, 210, 0))
						.setMaterial(new Material().setKd(0.3).setKs(0).set_nShininess(900).setKt(0).setKr(0)
								.setRadiusForGlossy(0.08)),
				new Sphere(new Point3D(100, 35, 0), 30).setEmmission(new Color(255, 210, 0)).setMaterial(
						new Material().setKd(0.3).setKs(0).set_nShininess(900).setKt(0).setKr(0).setRadiusForGlossy(0.08))

		);

		scene.lights.addAll(List.of(
				new SpotLight(new Color(1000, 600, 1000), new Point3D(-100, 100, 100), new Vector(1, -0.4, -1)).setKc(1)
						.setKl(0.0001).setKq(0.00005),
				new DirectionalLight(new Color(255, 215, 0), new Vector(-1, -0.4, 1))));
		int p = 500;
		ImageWriter imageWriter = new ImageWriter("GlassCube", p, p);
		Render render = new Render().setImageWriter(imageWriter).setCam(camera)
				.setRayTracerBase(new BasicRayTracer(scene).setDistanceForBlurryGlossy(80).setNumOfRaysGlossy(100))
				.setMultithreading(3).setDebugPrint();
		
		render.renderImage();
		render.writeToImage();
		// whitin glussy
		ImageWriter imageWriter1 = new ImageWriter("GlassCube whitin glossy", p, p);
		Render render1 = new Render().setImageWriter(imageWriter1).setCam(camera)
				.setRayTracerBase(new BasicRayTracer(scene)).setMultithreading(3).setDebugPrint();

		render1.renderImage();
		render1.writeToImage();
	}
}
