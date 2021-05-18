 package renderer;

import static primitives.Util.*;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
import elements.*;

/**
 * Ronni & Nov
 *
 * this class extends the RayTracerBase and implements the traceRay function.
 * this class in responsible to calculate the right color of the 
 * intersection point with the ray (through the pixel)
 */
public class BasicRayTracer extends RayTracerBase
{
	/**
	 * constructor that gets the scene we will trace and calls the father's constructor to set it.
	 * @param _scene
	 */
	public BasicRayTracer(Scene _scene)
	{
		super(_scene);
	}

	/**
	 * @param ray
	 * @return the color of the pixel that the ray pass through it
	 */
	public Color traceRay(Ray ray)
	{		
		GeoPoint pointAndGeo=ray.getClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
		if(pointAndGeo!=null)					//if there is an intersection point- calc it's color ð
			return calcColor(pointAndGeo, ray);
		else							//if the ray doesn't intersect anything- return the background color
			return scene.background;
	}

	/**
	 * calculates the color of the point that the ray intersect it 
	 * (we allready get here the closest intersection point)
	 * @param point
	 * @param the ray
	 * @return the color 
	 */
	public Color calcColor(GeoPoint pointAndGeo, Ray ray)
	{
		//in the beginning: the ambient light+the emission of the geometry.
		
		Color result= scene.ambientLight.getIntensity()
				.add(pointAndGeo.geometry.getEmmission());
		//The code that adds the effect of the light sources on the point for which the color is calculated 
		//according to the simple phong model
		//now add all light local effects in the scene
		result=result.add(calcLocalEffects(pointAndGeo,ray));
		
		return result;	

	}

	/**
	 * Adding diffusion/specular calculation
	 * this function calculates the local light effects in phonge model
	 * @param intersection
	 * @param ray
	 * @return the color that was calculated
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) 
	{
		Vector v = ray.getDir ();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) 
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
		double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) 
		{
			Vector l = lightSource.getL(intersection.point).normalized(); 
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv) 
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}

	/**
	 * Calculate Specular component of light reflection.
	 *
	 * @param ks         specular component coef
	 * @param l          direction from light to point
	 * @param n          normal to surface at the point
	 * @param v          direction from point of view to point
	 * @param nShininess shininess level
	 * @param ip         light intensity at the point
	 * @return specular component light effect at the point
	 * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
	 * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
	 * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
	 * the surface.
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color ip) 
	{
		double p = Util.alignZero(nShininess);
		
		double nl = Util.alignZero(n.dotProduct(l));//the light normal
		
		Vector R = l.substract(n.scale(2 * nl)).normalized(); // nl must not be zero!
		double minusVR = -Util.alignZero(R.dotProduct(v));
		if (minusVR <= 0) 
		{
			return Color.BLACK; // view from direction opposite to r vector
		}
		return ip.scale(ks * Math.pow(minusVR, p));
	}

	/**
	 * Calculate Diffusive component of light reflection.
	 *
	 * @param kd diffusive component 
	 * @param l          direction from light to point
	 * @param n          normal to surface at the point
	 * @param ip light intensity at the point
	 * @return diffusive component of light reflection
	 * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
	 * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
	 * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
	 * be a color defined as: [rd,gd,bd](n•L)
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color ip) 
	{
		double nl = Util.alignZero(n.dotProduct(l));//the light normal
		
		if (nl < 0) 
			nl = -nl;
		return ip.scale(nl * kd);
	}

}
