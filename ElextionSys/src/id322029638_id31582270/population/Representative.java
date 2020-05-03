package id322029638_id31582270.population;

import id322029638_id31582270.Party;

public class Representative extends Citizen {
	private Party underParty;

	public Representative(Citizen voter) {
		super(voter);
		// TODO Auto-generated constructor stub
	}

	public Representative(String name, String id, String birthYear, boolean underQuarantine, boolean protectionGear) {
		super(name, id, birthYear, underQuarantine, protectionGear);
		// TODO Auto-generated constructor stub
	}

	public Party getUnderParty() {
		return underParty;
	}

	public void setUnderParty(Party underParty) {
		this.underParty = underParty;
	}

	@Override
	public String toString() {
		return super.getName() + "represents " + underParty;
	}

}
