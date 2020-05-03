package id322029638_id31582270.population;

import id322029638_id31582270.BBox;

public class Solider extends Citizen {
	private BBox<Solider> votesAtBallotBox;

	boolean carryWeapon;

	public Solider(Citizen voter) {
		super(voter);
	}

	public Solider(String name, String id, String birthYear, boolean underQuarantine,boolean protectionGear) {
		super(name, id, birthYear, underQuarantine,protectionGear);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canVote() {
		if (this.isInArmy()) {
			return true;
		}
		return false;
	}
	

}
