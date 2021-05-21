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
	public double kD=0;	//difusive mekadem--how much the light obsetves into the material
    public double kS=0;	//specular mekadem--how much the light is returnad by the material
    public int nShininess=0;//shininess of the material
    public double kr=0.0;//mekadem 'hishtakfut'
    public double kt=0.0;//mekadem shkifut
    //******************setters****************
    /**
     * set kd-difusive mekadem--how much the light obsetves into the material
     * @param _kD
     * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
     */
	public Material setKd(double _kD) 
	{
		this.kD = _kD;
		return this;
	}
	/**
	 * set ks--specular mekadem--how much the light is returnad by the material
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
	/**
	 * set kr--reflection-- mekadem hishtakfut
	 * @param _kr
	 * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Material setKr(double _kr) 
	{
		this.kr = _kr;
		return this;
	}  
	/**
	 * set kt--transparency--refraction--mekadem shkifut
	 * @param _kt
	 * @return the material itself to allow design pattern of builder- to concatenate calls to setters.
	 */
	public Material setKt(double _kt) 
	{
		this.kt = _kt;
		return this;
	}  
}
