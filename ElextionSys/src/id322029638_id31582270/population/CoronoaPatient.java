package id322029638_id31582270.population;


import id322029638_id31582270.interfaces.SickMarker;

public class CoronoaPatient extends Voter implements SickMarker {
	private int daysInfected;

	public CoronoaPatient(Voter voter, int daysInfected) {
		super(voter);
		this.setDaysInfected(daysInfected);
	}
@Override
	public int getDaysInfected() {
		return daysInfected;
	}
@Override
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
