package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;

public class InfectedSolider extends Voter implements SickMarker, SoliderMarker {
	protected Solider solider;
	protected CoronoaPatient patient;

	public InfectedSolider(Voter voter) {
		super(voter);
		this.solider = new Solider(voter, false);
		this.patient = new CoronoaPatient(voter, 0);
	}
@Override
	public int getDaysInfected() {
		return patient.getDaysInfected();
	}
@Override
	public void setDaysInfected(int daysInfected) {
		this.patient.setDaysInfected(daysInfected);
	}
@Override
	public boolean isCarryWeapon() {
		return solider.isCarryWeapon();
	}
	public void printWeaponStatus() {
		if (solider.isCarryWeapon())
			System.out.println("I got my M-16.");
		if (!solider.isCarryWeapon())
			System.out.println("I left my M-16 at the armory.");

	}

	@Override
	public void setCarryWeapon(boolean carryWeapon) {
		this.solider.setCarryWeapon(carryWeapon);
	}
}
