package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Light source is far away - no attenuation with distance. like the sun.
 * 
 * nov & ronni
 * 
 */
public class DirectionalLight extends Light implements LightSource
{
	private Vector direction;		//the direction of directional light source

	/* ********* Constructors ***********/

	/**
	 * a new Directional Light
	 *
	 * @param color color
	 * @param direction light direction
	 */
	public DirectionalLight(Color color, Vector direction) 
	{
		super(color);
		this.direction = direction.normalized();
	}

	/* ************* Getters/Setters *******/
	/**
	 * get light intensity
	 * @param p the point
	 * @return light intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return super.getIntensity();
	}

	/**
	 * get vector of the direction of the light from 'light'
	 * @param p the point
	 * @return vector
	 */
	@Override
	public Vector getL(Point3D p) 
	{
		return direction;
	}

}