package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.SoliderMarker;

public class Solider extends Voter implements SoliderMarker {
	boolean carryWeapon;

	public Solider(Voter voter, boolean carryWeapon) {
		super(voter);
		this.setCarryWeapon(carryWeapon);
	}
	public Solider(Solider copy) {
		super(copy);
		this.setCarryWeapon(copy.isCarryWeapon());
	}
	@Override
	public boolean isCarryWeapon() {
		return carryWeapon;
	}

	public void printWeaponStatus() {
		if (carryWeapon)
			System.out.println("I got my M-16.");
		if (!carryWeapon)
			System.out.println("I left my M-16 at the armory.");

	}
	@Override
	public void setCarryWeapon(boolean carryWeapon) {
		this.carryWeapon = carryWeapon;
	}

	@Override
	public boolean canVote() {
		if (!(this.isCarryWeapon())) {
			return true;
		}
		return false;
	}

}
