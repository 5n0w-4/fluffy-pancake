package id322029638_id31582270.population;

import id322029638_id31582270.exceptions.Invalid_Age;
import id322029638_id31582270.exceptions.Invalid_Id;
import id322029638_id31582270.interfaces.SoliderMarker;

public class Solider extends Voter implements SoliderMarker {
	boolean carryWeapon;

	public Solider(Voter voter, boolean carryWeapon) throws Invalid_Age, Invalid_Id {
		super(voter);
		this.setCarryWeapon(carryWeapon);
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

	@Override
	public boolean canVote() {
		if (!(this.isCarryWeapon())) {
			return true;
		}
		return false;
	}

}
