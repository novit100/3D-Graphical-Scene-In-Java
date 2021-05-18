package primitives;

/**
 * 
 * @author Nov & Ronni
 * this class contains the features of a material- the shinines, kd and ks,
 * all the parameters that affect the light comes from and to this material.
 * the class is PDS and goes by the builder design pattern 
 *
 */
public class Material 
{
	public double kD=0;	//difusive mekadem
    public double kS=0;	//specular mekadem
    public int nShininess=0;//shininess of the material
    
    //******************setters****************
    /**
     * set kd
     * @param _kD
     * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
     */
	public Material setKd(double _kD) 
	{
		this.kD = _kD;
		return this;
	}
	/**
	 * set ks
	 * @param _kS
	 * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Material setKs(double _kS) 
	{
		this.kS = _kS;
		return this;
	}
	/**
	 * set shininess
	 * @param _nShininess
	 * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Material set_nShininess(int _nShininess) 
	{
		this.nShininess = _nShininess;
		return this;
	}
    
    
}
