package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.logic.Party;

public class Representative extends Voter implements RepresentativeMarker {
	private Party underParty;

	public Representative(Voter voter, Party underParty) {
		super(voter);
		this.setUnderParty(underParty);
	}
	

	public Representative(Representative copy) {
		super((Voter)copy);
		this.setUnderParty(copy.getUnderParty());

	}

@Override
	public Party getUnderParty() {
		return underParty;
	}
@Override
	public void setUnderParty(Party underParty) {
		this.underParty = underParty;
	}

	@Override
	public String toString() {
		return super.getName() + "represents " + underParty;
	}

}
