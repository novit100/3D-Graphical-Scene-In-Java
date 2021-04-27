package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;
/**
 * Scene class represents a scene composed by the name,background,ambient light and geometries.
 * @author Ronni & Nov
 *this class is PSD and thats why all of the fields are public properties
 */
public class Scene {

	public String name;//the name of the scene
	public Color background;//the color of the background
	public AmbientLight ambientLight=new AmbientLight(Color.BLACK,1);//the abient light with default value -black
	public Geometries geometries;//a geometry model with default geometry value.
	////////////// ctor ////////////////////
	public Scene(String name) {
		this.name = name;
		geometries=new Geometries();
	}
	/////////////  set  /////////////////////
	/**
	 * gets a color and set's it in the scene.
	 * we are using the Builder design pattern,and that's why we returned the scene itself
	 * @param background
	 * @return scene
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
	/**
	 * gets the AmbientLight and set's it in the scene.
	 * we are using the Builder design pattern,and that's why we returned the scene itself
	 * @param background
	 * @return scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}
	/**
	 * gets the Geometries and set's theme in the scene.
	 * we are using the Builder design pattern,and that's why we returned the scene itself
	 * @param background
	 * @return scene
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	

}