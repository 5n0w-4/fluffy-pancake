package id322029638_id31582270.exceptions;

public class Invalid_Id extends Exception {
	private String id;

	public Invalid_Id(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	
	
}
