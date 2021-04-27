package renderer;


import java.util.MissingResourceException;
import elements.Camera;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import renderer.BasicRayTracer;


/**
 * Render class is destined to create the color matrix of the image from the scene.
 * the class will be built partly according to the Builder design pattern.
 * @author Ronni & Nov
 * no constructor in this class.
 */
public class Render {
 ImageWriter imageWriter;
 Scene scene;
 Camera cam;
 BasicRayTracer rtBasic;
 
//////////////////////// setters //////////////////////////////
/**
 * a setter function of the field image writer that returns the render object itself for concatenation (shirshur)
 * @return this 
 */
public Render setImageWriter(ImageWriter imageWriter) {
	this.imageWriter = imageWriter;
	return this;
}
/**
 * a setter function of the field scene ,that returns the render object itself for concatenation (shirshur)
 * @return this 
 */
public Render setScene(Scene scene) {
	this.scene = scene;
	return this;
}
/**
 * a setter function of the field cam ,that returns the render object itself for concatenation (shirshur)
 * @return this 
 */
public Render setCam(Camera cam) {
	this.cam = cam;
	return this;
}
/**
 * a setter function of the field rtBasic ,that returns the render object itself for concatenation (shirshur)
 * @return this 
 */
public Render setRtBasic(BasicRayTracer rtBasic) {
	this.rtBasic = rtBasic;
	return this;
}

///////////////////////// functions ////////////////////////////////////
/**
 * .................
 */
public void renderImage() {
	if (imageWriter==null 
			||scene==null 
		    ||cam==null
		    ||rtBasic==null)
		throw new MissingResourceException("not all the fields contain a value", "Render", null); 
	BasicRayTracer hlp =new BasicRayTracer(scene);
	int Nx = imageWriter.getNx();
    int Ny = imageWriter.getNy();
    for (int i = 0; i < Ny; i++) {
        for (int j = 0; j < Nx; j++) {
        	
        	Ray r=cam.constructRayThroughPixel(Nx,Ny,j,i); // a ray with the camera starting point
         imageWriter.writePixel(j, i, hlp.traceRay(r));
        }
    }
  
}
/**
 * a function that creates a grid and colors only the stripes
 * @param interval
 * @param color
 */
public void printGrid(int interval, Color color) {
	if(imageWriter==null)
		throw new MissingResourceException("no image exists yet", "Render", null); 
	int Nx = imageWriter.getNx();
    int Ny = imageWriter.getNy();
    for (int i = 0; i < Ny; i++) {
        for (int j = 0; j < Nx; j++) {
            if (i % interval == 0 || j % interval == 0)
            {
                imageWriter.writePixel(j, i, Color.GREEN);//coloring only the stripes of the grid
            }
        }
    }
    imageWriter.writeToImage();
}
/**
 * a function that has the responsibility to write to the image, and it doe's so by delegating 
 * the task to the relevant function in imageWriter class.
 */
public void writeToImage() {
	if(imageWriter==null)
		throw new MissingResourceException("no image exists yet", "Render", null); 
	   imageWriter.writeToImage();
}

}
