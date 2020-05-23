package id322029638_id31582270.exceptions;

public class Invalid_Age extends Exception {
	private String birthYear;

	public Invalid_Age(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getAge() {
		return birthYear;
	}
	
	

}
