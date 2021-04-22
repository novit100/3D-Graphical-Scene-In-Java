package elements;

import primitives.Color;
/**
 * AmbientLight class represents the light of the environment
 * @author Ronni & Nov
 *
 */
public class AmbientLight {
	Color intensity; 
	double Ka; //the discount factor of the ambient light
	//ka=the intensity of the light by the RGB components
	////////////// ctor /////////////////////
	public AmbientLight(Color ia, double ka) {
		intensity=new Color(ia.scale(ka));
	}
	/***
	 * @return the intensity of the ambient light
	 */
	public Color getIntensity() {
		return intensity;
	}

}
