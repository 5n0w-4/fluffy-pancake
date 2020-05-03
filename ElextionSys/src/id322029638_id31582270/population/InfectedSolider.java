package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;

public class InfectedSolider extends Voter implements SickMarker, SoliderMarker {
	private int daysInfected;
	boolean carryWeapon;

	public InfectedSolider(Voter voter, int daysInfected, boolean carryWeapon) {
		super(voter);
		this.daysInfected = daysInfected;
		this.carryWeapon = carryWeapon;
	}
@Override
	public int getDaysInfected() {
		return daysInfected;
	}
@Override
	public void setDaysInfected(int daysInfected) {
		this.daysInfected = daysInfected;
	}

	public boolean isCarryWeapon() {
		return carryWeapon;
	}

	public void printWeaponStatus() {
		if (carryWeapon)
			System.out.println("I got my M-16.");
		if (!carryWeapon)
			System.out.println("I left my M-16 at the armory.");

	}

	public void setCarryWeapon(boolean carryWeapon) {
		this.carryWeapon = carryWeapon;
	}
}
