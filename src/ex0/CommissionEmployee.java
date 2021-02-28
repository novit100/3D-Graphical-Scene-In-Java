package ex0;

public class CommissionEmployee extends Employee {
	float grossSales;
	int commision;
public CommissionEmployee(String fN, String lN, int id_, float grossSales, int commision) {
		super(fN, lN, id_);
		this.grossSales = grossSales;
		this.commision = commision;
	}
public CommissionEmployee() {
	super();
	this.grossSales = 0;
	this.commision = 0;
}
public double earnings() {
		
		return (double)(((float)commision/100)*grossSales);
	}
@Override
public String toString() {
	return super.toString()+"\nCommissionEmployee grossSales=" + grossSales + ", commision=" + commision;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	CommissionEmployee other = (CommissionEmployee) obj;
	if (commision != other.commision)
		return false;
	if (Float.floatToIntBits(grossSales) != Float.floatToIntBits(other.grossSales))
		return false;
	return true;
}

}
