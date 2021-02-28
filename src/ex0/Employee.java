package ex0;

public abstract class Employee {

	String firstName ;
	String lastName;
	int id;
	Employee(String fN,String lN,int id_){
		firstName=fN;
		lastName=lN;
		id=id_;
	}
	Employee(){
		firstName="plony";
		lastName="almony";
		id=0;
	}
	String get_firstName() {
		return firstName;
	}
	String get_lastName() {
		return lastName;
	}
	int get_id() {
		return id;
	}
	public void set_firstName(String fN) {
		firstName=fN;
	}
	public void set_lastName(String lN) {
		lastName=lN;
	}
	public void set_id(int id_) {
		id=id_;
	}
	public abstract double earnings();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	@Override
	public  String  toString() {
		return String.format(firstName+ " "+lastName+" "+'\n'+"id:"+id);
	}
}
