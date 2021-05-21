package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface LightSource for all the lights with source (not ambient)
 * 
 * @author Ronni & Nov
 */

public interface LightSource 
{
        /**
         * get Color Intensity of a certain point
         *
         * @return color Intensity
         */
        Color getIntensity(Point3D p);

        /**
         * get the direction of the light to a point
         *
         * @param p the point
         * @return normalized vector
         */
        Vector getL(Point3D p);
        /**
         * get distance of lightsource from point
         *
         * @param p the point
         * @return the distance
         */
        double getDistance(Point3D point);
        
}