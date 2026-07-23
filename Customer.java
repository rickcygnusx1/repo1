import java.util.ArrayList;
import java.util.List;

// this is a test string for the third time
// this is the next test string
//**************************************************
// this is the original Customer file and this line is created on the new branch branch1

class Customer implements Cr1, Cr2 {
	private String lastName;
	private String phoneNumber = "000-000-0000";
	private int type; // 1:Corp, 2:Individual
	private static int count = 0;
	private static final String store="my store";

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
	public Customer getall() { return this; }
	public static void getStatic() { System.out.println(Customer.store + ", " + Customer.count); }

	Customer() {
		this.lastName = "blank";
		this.type = 0;
		Customer.count++;
	}

	public static void main(String[] args) {
		int[] ar1=new int[10];
		int[] ar2=new int[10];
		for(int x=0; x<10; x++) {
			ar1[x]=x*5;
			System.out.println(ar1[x]);
		}
		System.out.println("length: "+ar1.length);
		System.arraycopy(ar1,3,ar2,1,4);
		for(int x=0; x<10; x++) System.out.println(ar2[x]);
	}
}

interface Cr1 {
	String getLastName();
	void setLastName(String lastName);
}

interface Cr2 {
	public String getPhoneNumber();
	void setPhoneNumber(String phoneNumber);
	int getType();
	void setType(int type);
	Customer getall();
}

class YoungCustomer extends Customer {
	private int age;
	private String hobby;
	// NOTE: Since this class does not have a constructor, the compiler automatically calls the superclass's no-argument constructor
	void setage(int fl1) { age=fl1; }
	int getage() { return this.age; }
	void sethobby(String fl1) { this.hobby=fl1; }
	String gethobby() { return hobby; }
	public void setPhoneNumber(String pr1) {
		System.err.println("OVERRIDING setPhoneNumber");
		super.setPhoneNumber(pr1);	//NOTE: the super keyword is used INSIDE this method to call another method in the superclass
	}
}

class Utility {

	static Customer covariantReturnType(YoungCustomer fl1) {
	fl1.setLastName("James");
	fl1.setage(19);
	fl1.sethobby("waterskiing");
	return fl1;
	}

	public static void main(String[] args) {
	Customer c1=new Customer();
	c1.setLastName("Dangelo");
	YoungCustomer c2=new YoungCustomer();	// Since this class does not have a constructor, the compiler automatically calls the superclass's no-argument constructor
	c2.setLastName(c1.getall().getLastName());
	c2.setPhoneNumber("090-1234-5678");
	Customer.getStatic();
	c1 = covariantReturnType(c2);	/* NOTE: object c1 has to be of the same class as the stated return type of the function, 
	or else it will not compile. Since in this case the class is Customer, c1 will not receive YoungCustomer's fields */

	System.out.println("c1 instanceof Cr1 : "+String.valueOf(c1 instanceof Cr1));
	System.out.println("c1.getLastName() : "+c1.getLastName());
	System.out.println("c2.getLastName() : "+c2.getLastName());
	System.out.println("c1.getPhoneNumber() : " + c1.getPhoneNumber());
	}
}
