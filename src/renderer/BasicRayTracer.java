package renderer;

import static primitives.Util.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
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
{//////fields///
	/**
	 * a constant for size moving first rays for shading rays
	 */
	private static final double DELTA = 0.01;

	private static final double INITIAL_K = 1.0;
	/**
	 * Two constants for stopping conditions
	 *  in the recursion of transparencies / reflections
	 */
	/**
	 * recursion stop condition - the maximum number of colors
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * recursion stop condition -
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

	private boolean isImprovement=true;//a boolean field that tells us if we want to display the image with improvement or not
	/////////////////////////////////////////////
	private int NumOfRaysGlossy=0; //the number of rays that we want to 
	/**
	 * distance form the begining of the beam to the circle random grid 
	 */
	private int NumOfRaysBlurry=0; //the number of rays that we want to 
	/**
	 * distance form the begining of the beam to the circle random grid 
	 */
	private int distanceForBlurryGlossy=0;
	////////////////////////////////////////////
	/**
	 * constructor that gets the scene we will trace and calls the father's constructor to set it.
	 * @param _scene
	 */
	public BasicRayTracer(Scene _scene)
	{
		super(_scene);
	}
	//////////////////   setter     /////////////////////////////////////



	public BasicRayTracer setNumOfRaysGlossy(int numOfRay) {
		this.NumOfRaysGlossy = numOfRay;
		return this;
	}


	public BasicRayTracer setNumOfRaysBlurry(int numOfRay) {
		this.NumOfRaysBlurry = numOfRay;
		return this;
	}
	public BasicRayTracer setDistanceForBlurryGlossy(int distanceForBlurryGlossy) {
		this.distanceForBlurryGlossy = distanceForBlurryGlossy;
		return this;
	}

	//////////////     functions    //////////////////////////////////////////		
	/**CHANGED THE FUNCTION IN STAGE 8 -MINI 1
	 * @param ray
	 * @return the color of the pixel that the ray pass through it

	public Color traceRay(Ray ray)
	{		
		GeoPoint pointAndGeo=findClosestIntersection(ray);//calls the function of findinf the closest intersection- the func is in this class 
		if(pointAndGeo!=null)					//if there is an intersection point- calc it's color 
			return calcColor(pointAndGeo, ray);
		else							//if the ray doesn't intersect anything- return the background color
			return scene.background;
	}
	 */
	/**
	 * 
	 */
	@Override
	public Color traceRay(Ray ray)
	{
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/**
	 * @param curRay
	 * @return GeoPoint to the ray closest intersection Point
	 */
	private GeoPoint findClosestIntersection(Ray curRay) {

		if (curRay == null) {
			return null;
		}

		GeoPoint closestPoint = null;
		double closestDistance = Double.MAX_VALUE;
		Point3D ray_p0 = curRay.getP0();//the starting point of the ray
		//technically we don't need this variable but we used it in order not to call
		//the function too many times in case there are many intersections
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(curRay);
		if (intersections == null)
			return null;//no intersection
		double distance=0;
		for (GeoPoint geoPoint : intersections) //select the closest distance
		{
			distance = ray_p0.distance(geoPoint.point);
			if (distance < closestDistance) 
			{
				closestDistance = distance;
				closestPoint = geoPoint;
			}
		}
		return closestPoint;
	}
	/**
	 * setter to os Ipmrovement
	 * @param isImp
	 * @return
	 */
	public BasicRayTracer setIsimprovement(boolean isImp) {
		this.isImprovement=isImp;
		return this;
	}
	/**
	 * Non-shading test operation between point and light source
	 * @param lS -the current light source 
	 * @param l a vector from the light source to the point 
	 * @param norm
	 * @param gp
	 * @return if the point has no shade return true (and then it'll get light)
	 */
	private boolean unshaded(LightSource lS,Vector l, Vector norm, GeoPoint gp) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = norm.scale(norm.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);//if the result is greater than 0 delta=DELTA else delta=-DELTA
		//there are cases that points in the object can falsely be shaded by the object itself-->  so we used the delta to up lift
		//the point a-bit to avoid that
		Point3D point = gp.point.add(delta);//we add it to the point by the normal direction,

		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)//if there are no intersections with the light ray
			return true;//the point is unshaded- no one makes shadow on the point
		//else- there are intersections with the light ray
		double lightDistance = lS.getDistance(gp.point);//the distance of the point of geoPoint from the light source
		for (GeoPoint geop : intersections) 
		{
			if(geop.geometry.getMaterial().kt==0.0)//only if the material of the geo is "atum"- it makes shadow
			{
				//if there is an intersection closer to beginning of ray than our intersection
				//of geopoint that we got- return false. 
				//it means, there is something shadowing our intersection of geopoint.
				if (Util.alignZero(geop.point.distance(gp.point)-lightDistance) <= 0)
					return false;
			}
		}
		//else- if we didn't find an intersection that is closer to the head of the ray from the distance
		//between the point to the light source:
		return true;	
	}
	/**
	 * as func unshaded but returns different val
	 * returns the amount of the shading
	 * @param ls
	 * @param l
	 * @param norm
	 * @param intersection
	 * @return the transparency 
	 */
	private double transparency(LightSource ls, Vector l, Vector norm, GeoPoint intersection) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(intersection.point, lightDirection, norm);//taking care of----
		double lightDistance = ls.getDistance(intersection.point); //the distance of the point of Point from the light source
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return 1.0;

		double ktr = 1.0;//transparency initial

		for (GeoPoint gp : intersections) //move on all the geometries in the way
		{
			//if there are geometr between the point to the light- calculate the transparency 
			//in order to know how much shadow there is on the point
			if (Util.alignZero(gp.point.distance(intersection.point)-lightDistance) <= 0) 
			{
				ktr *= gp.geometry.getMaterial().kt;//add this transparency to ktr
				if (ktr < MIN_CALC_COLOR_K) //stop the loop- shadow "atum", black. very small transparency
					return 0.0;
			}
		}
		return ktr;
	}


	///////////////////////////////////// calculations ////////////////////////////////////////

	/**
	 * calculates the color of the point that the ray intersect it 
	 * (we allready get here the closest intersection point)
	 * @param point
	 * @param the ray
	 * @return the color 
	 */
	public Color calcColor(GeoPoint closestPoint, Ray ray)
	{
		//according to stage 7 we removed the ambient light
		// We added the shading test in the function
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());



		/**
		 * we removed this code in

		//in the beginning: the ambient light+the emission of the geometry.

		Color result= scene.ambientLight.getIntensity()
				.add(pointAndGeo.geometry.getEmmission());
		//The code that adds the effect of the light sources on the point for which the color is calculated 
		//according to the simple phong model
		//now add all light local effects in the scene
		result=result.add(calcLocalEffects(pointAndGeo,ray));

		return result;	
		 */
	}

	/**
	 * calculates the color of the point that the ray intersect it 
	 * (we already get here the closest intersection point)
	 * @param point
	 * @param the ray
	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return the color 
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) 
	{
		//in the recursive calls to calcColor we might get that intersections=null.
		//if so- return black. no adding color and light.
		if(intersection==null)
			return Color.BLACK;

		Color color = intersection.geometry.getEmmission();//the color of the object itself
		color = color.add(calcLocalEffects(intersection, ray, level, k));//diffuse, specular, and shadow.
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));//reflection and refraction- may cause recursion
	}

	/**
	 * calc the global effects- relection and refraction.
	 * this func call "calcColor" in recursion to add more and more global effects.
	 * @param intersection
	 * @param ray
	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray inRay, int level, double k) {
		Vector n = geopoint.geometry.getNormal(geopoint.point);//normal to geometry in point --need this parameter for construct reflected\refracted ray

		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();


		double kr = material.kr; 
		double kkr = k * kr;
		double kt = material.kt;
		double kkt = k * kt;


		//improve: check the conditions in the beginning
		if (level == 1 || k < MIN_CALC_COLOR_K) //too much levels or isn't reflected/refracted anymore
		{
			return color; 
		}
		//the calculation of the reflected 

		if (kkr > MIN_CALC_COLOR_K) //stop recursion when it gets to the min limit
		{Ray ray = constructReflectedRay(geopoint.point,inRay,n);
		if(isImprovement==true) {
			try {
				color = calcBeamColor(material.radiusForGlossy, NumOfRaysGlossy, color, n, ray, level, kr, kkr);
			} 



			catch(Exception e)
			{}
		}
		else //if we don't want to improve
		{
			color = calcGlobalEffect(ray, level - 1, kr, kkr);


		}
		}	
		//the calculation of the refracted 

		if (kkt > MIN_CALC_COLOR_K) //stop recursion when it gets to the min limit
		{
			Ray ray1 = constructRefractedRay(n,geopoint.point, inRay.getDir());////
			if(isImprovement==true) {

				try {
					color = calcBeamColor(material.radiusForBlurry, NumOfRaysGlossy, color, n.scale(-1), ray1, level, kt, kkt);
				}

				catch(Exception e)
				{}}

			else	//if we don't want to improve the pic
			{
				color = calcGlobalEffect(ray1, level - 1, kt, kkt);
			}

		}
		return color;
	}




	/**
	 * compute reflections and refractions effects in a point
	 * 
	 * @param ray   direction of the light
	 * @param level level of recursion
	 * @param kx    coefficient
	 * @param kkx   coefficient
	 * @return color of the point
	 */
	private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
		GeoPoint gp = findClosestIntersection(ray);
		return ((gp == null) ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}


	/**
	 * return average color
	 * @param listColor
	 * @return color
	
	private Color averageColor(List<Color> listColor) {
		int red=0, green=0,blue=0;
		for (int i = 0; i < listColor.size(); i++) {
			red+= listColor.get(i).getColor().getRed();
			green+= listColor.get(i).getColor().getGreen();
			blue+= listColor.get(i).getColor().getBlue();
		}
		return new Color(red/listColor.size(),green/listColor.size(),blue/listColor.size());
	}
 */

	/**
	 * Adding diffusion/specular calculation, and check shadow
	 * this function calculates the local light effects in phonge model
	 * @param intersection
	 * @param ray
	 * 	 * @param level of recursion- goes down each time till it gets to 1
	 * @param k- mekadem of reflection and refraction so far
	 * @return the color that was calculated
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, int level, double k) 
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
			if (nl * nv > 0) 
			{ 
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) //if the object isn't sealed (opacity-'ATUM') 
					//and it has transparency so calculate the amount of shadow according to that.
				{	
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);//ktr=transparency- for shadow
					;
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),//diffusive
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));//specular
				}
				//else-it doen't got transparency and only got's opacity -> so don't calculate the light
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
	 * The diffuse component is that dot product nL that we discussed in class. It approximates light, originally
	 * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
	 * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
	 * be a color defined as: [rd,gd,bd](nL)
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color ip) 
	{
		double nl = Util.alignZero(n.dotProduct(l));//the light normal

		if (nl < 0) 
			nl = -nl;
		return ip.scale(nl * kd);
	}


	///////////////// construct ray ///////////////////////////////////////////////


	/**
	 * construct the refracted ray
	 * 
	 * @param n     vector "on" the solid
	 * @param point pointed by the vector n, on the solid to
	 * @param inRay direction of the refracted vector
	 * @return refracted ray -- this func returns a new ray- the refracted ray comes from the point because of the light-ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Vector v) {
		return new Ray(point, v, n);
	}


	/**
	 * this func returns a new ray- the reflected ray comes from the point because of the light- inRay
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return ReflectedRay
	 */

	private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) 
	{
		Vector v = inRay.getDir();
		double vn = v.dotProduct(n);

		if (Util.isZero(vn)) 
		{
			return null;
		}
		Vector r = v.substract(n.scale(2 * vn));
		return new Ray(pointGeo, r, n);
	}


	/**
	 * this function calculates the color of a specific point according to the color of each 
	 * point in the beam (the final color of the point will be the average color of beam)
	 * @param radius   -a chosen number to create the size of the random grid 
	 * @param numOfRaysInBeam -the number of rays we want in our beam
	 * @param color    -the color of the intersection point --this color starts with black in the func calcGlobal and changes within the code 
	 * @param n        -The normal vector of the point where beam start
	 * @param refRay   -reflected/refracted ray
	 * @param level    -The level of recursion
	 * @param k        - kt/kr
	 * @param kk
	 * @return the average color 
	 */
	private Color calcBeamColor(double radius, int numOfRaysInBeam, Color color, Vector n, Ray refRay, int level, double k, 
			double kk) {
		Color addColor = Color.BLACK;
		List<Ray> rays = null;
		rays = refRay.raySplitter(n, numOfRaysInBeam, radius, distanceForBlurryGlossy);
		for (Ray ray : rays) {
			addColor = addColor.add(calcGlobalEffect(ray, level - 1, k, kk));
		}
		int size = rays.size();
		color = color.add(addColor.reduce(size));
		return color;
	}
	//mini project 2 
	/**
	 * recursive function which calculate the color of the pixel by checks if all corners are equal if not calculate the color of each quarter
	 *
	 * @param points
	 * @param level
	 * @param focalPoint
	 * @return color pixel
	
	private Color calcColorPixel4(List<Point3D> points, int level, Point3D focalPoint)
	{

		Camera camera = _scene.getCamera();

		double width = camera.getWidthSh();
		double height = camera.getHeightSh();

		List<Ray> rays = constructRaysThroughPixel(points, focalPoint);
		Ray r = rays.remove(0);
		GeoPoint closestPoint = findCLosestIntersection(r);
		Color cr = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, r));

		if(level>=3)
		{
			return cr;
		}

		cr.reduce(4);
		Color color = cr;
		for(Ray ray:rays)
		{
			closestPoint = findCLosestIntersection(ray);
			Color c = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray)).reduce(4);
			if(!c.getColor().equals(cr.getColor()))
			{
				List<Point3D> centerP = findCenterNewPixels(points);
				Color colors = new Color(0,0,0);
				for(Point3D p: centerP)
				{
					List<Point3D> newPoints = camera.getPointsPixel(p, width/(Math.pow(2, level)), height/(Math.pow(2, level)));
					colors = colors.add(calcColorPixel4(newPoints, level+1, focalPoint).reduce(4));
				}
				return colors;
			}
			color = color.add(c);
		}

		return color;
	}
 */

	 /**
     * @param rays List of surrounding rays
     * @return average color
     */
	@Override
	public Color calcColorForSupersampling(List<Ray> rays) {
		
			Color bkg = scene.background;
			Color color = Color.BLACK;
			for (Ray ray : rays) {
				GeoPoint gp = findClosestIntersection(ray);
				color = color.add(gp == null ? bkg : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d));
			}
			color = color.add(scene.ambientLight.getIntensity());
			int size = rays.size();
			return (size == 1) ? color : color.reduce(size);
		}

}

