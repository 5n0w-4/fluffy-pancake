package id322029638_id31582270.population;

import org.hamcrest.core.IsNot;

import id322029638_id31582270.BBox;

public class CoronoaPatient extends Citizen {
	private BBox<CoronoaPatient> votesAtBallotBox;
	private int daysInfected;

	public CoronoaPatient(Citizen voter) {
		super(voter);
	}


	
	public CoronoaPatient(String name, String id, String birthYear, boolean underQuarantine,boolean protectionGear) {
		super(name, id, birthYear, underQuarantine,protectionGear);
		// TODO Auto-generated constructor stub
	}




	public int getDaysInfected() {
		return daysInfected;
	}

	public void setDaysInfected(int daysInfected) {
		this.daysInfected = daysInfected;
	}

	@Override
	public boolean canVote() {
		if (this.isProtectionGear()) {
			return true;
		}
		return false;
	}

}
