package ex0;

import java.text.DecimalFormat;
import java.util.Iterator;

public class Payroll {

	public static void main(String[] args) {

		Employee[] genericArray=new Employee[3];
		genericArray[0]=new HourlyEmployee("Ronni","Cohen",-3,100,20);
		genericArray[1]=new CommissionEmployee("Dan","Zilber",301,100000.5f,9);
		genericArray[2]=new BasePlusCommissionEmployee("Nov","Segal",209,50000,9,3000.0f);
		DecimalFormat df = new DecimalFormat("###.##");
		
		
		for (int i = 0; i < genericArray.length; i++) {
		//	if((genericArray[i].getClass().getSimpleName())!=" BasePlusCommissionEmployee") {
			if(i<2) {
				
				System.out.println(genericArray[i]+" ");	
				System.out.println(df.format(genericArray[i].earnings()));
				
			}
			else
				System.out.println(genericArray[i]+" "+df.format(genericArray[i].earnings()*1.1));
		}

	
}

}
