package ex0;

public class BasePlusCommissionEmployee extends CommissionEmployee{
	float baseSalary;//salary of a week

	public BasePlusCommissionEmployee(String fN, String lN, int id_, float grossSales, int commision, float baseSalary_) {
		super(fN, lN, id_, grossSales, commision);
		if (baseSalary_<0)
			throw new IllegalArgumentException(" cannot be a negative number");
		this.baseSalary = baseSalary_;
	}
	public BasePlusCommissionEmployee() {
		super();
		this.baseSalary = 0;
	}
	public float getBaseSalary() {
		if (baseSalary<0)
			throw new IllegalArgumentException(" cannot be a negative number");
		return baseSalary;
	}
	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	@Override
	public String toString() {
		return super.toString()+"\nBasePlusCommissionEmployee:baseSalary=" + baseSalary;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasePlusCommissionEmployee other = (BasePlusCommissionEmployee) obj;
		if (Float.floatToIntBits(baseSalary) != Float.floatToIntBits(other.baseSalary))
			return false;
		return true;
	}

	public double earnings() {
		return baseSalary+super.earnings();
	}
}
