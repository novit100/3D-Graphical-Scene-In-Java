package ex0;

public class BasePlusCommissionEmployee extends CommissionEmployee{
	float baseSalary;//salary of a week

	public BasePlusCommissionEmployee(String fN, String lN, int id_, float grossSales, int commision, float baseSalary) {
		super(fN, lN, id_, grossSales, commision);
		this.baseSalary = baseSalary;
	}
	public BasePlusCommissionEmployee() {
		super();
		this.baseSalary = 0;
	}
	public float getBaseSalary() {
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
