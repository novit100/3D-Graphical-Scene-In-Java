package elements;

import primitives.Color;

/**
 * Abstract class- light (the basic for light sources)
 * this class is not a public class
 * @author nov & ronni
 *
 */
abstract class Light 
{

	private Color intensity;//the intensity of the light
	

	/* ************* constructors *******/
	/**
	 * A new Light
	 * @param color the color
	 */
	protected Light(Color color)
	{
		intensity = new Color(color);
	}

	/* ************* Getters *******/
	/**
	 * get Color Intensity
	 * @return color Intensity
	 */
	public Color getIntensity() 
	{
		return intensity;
	}



}


