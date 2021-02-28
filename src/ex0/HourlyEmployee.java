package ex0;

public class HourlyEmployee extends Employee {
    float wages;
    int hours;
    
	public HourlyEmployee() {
		super();
		hours=0;
		wages=0;
	}

	public HourlyEmployee(String fN, String lN, int id_,float Wages_,int Hours_) {
		super(fN,lN,id_);
		if (Wages_<0||Hours_<0)
			throw new IllegalArgumentException("the wages cannot be a negative number");
		wages=Wages_;
		hours=Hours_;
	}

	@Override
	public String toString() {
		return super.toString()+'\n'+"HourlyEmployee: wages=" + wages + ", hours=" + hours;
	}

	public float getWages() {
		return wages;
	}

	public void setWages(float wages) {
		if (wages<0)
		throw new IllegalArgumentException("the wages cannot be a negative number");
		this.wages = wages;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		if (wages<0)
			throw new IllegalArgumentException("the hours cannot be a negative number");
		this.hours = hours;
	}

	@Override
	public double earnings() {
		
		return ((double)hours)*(double)(wages);
	}

	
  
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HourlyEmployee other = (HourlyEmployee) obj;
		if (hours != other.hours)
			return false;
		if (Float.floatToIntBits(wages) != Float.floatToIntBits(other.wages))
			return false;
		return true;
	}

}
