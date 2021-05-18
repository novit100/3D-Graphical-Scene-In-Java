package elements;

import primitives.Color;
/**
 * AmbientLight class represents the light of the environment
 * @author Ronni & Nov
 *
 */
public class AmbientLight extends Light{
	//Color intensity;  we were instructed to delete this field because we extended the class from 'light' that has intensity itself 
	double Ka; //the discount factor of the ambient light
	           //ka=the intensity of the light by the RGB components
	////////////// ctor /////////////////////
	public AmbientLight() {
		super(Color.BLACK);
	}
	/**
	 * sends to the constructor of 'light' -there it places the value of intensity
	 * @param ia
	 * @param ka
	 */
	public AmbientLight(Color ia, double ka) {
		super(ia.scale(ka));
	}
	/*** we were instructed to delete this field because we extended the class from 'light' that has intensity itself 
	 * @return the intensity of the ambient light
	public Color getIntensity() {
		return intensity;
	}
 */
}
