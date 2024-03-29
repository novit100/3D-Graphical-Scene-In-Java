package renderer;

import java.util.List;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase class is an abstract class that traces the ray through the scene 
 * @author Ronni & Nov
 *
 */
public abstract class RayTracerBase {
protected Scene scene;// a scene
/////////////////////// ctor ///////////////
/**
 * a constructor that gets a scene and sets it in the class field
 * @param scene
 */
public RayTracerBase(Scene scene) {
	this.scene = scene;
}
/**
 * an abstract method that get's a ray and returns a color
 * @param ray
 * @return color
 */
public abstract Color traceRay(Ray ray) ;
public abstract Color calcColorForSupersampling(List<Ray> rays);
}
