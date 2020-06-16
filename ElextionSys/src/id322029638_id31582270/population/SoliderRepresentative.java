package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;
import id322029638_id31582270.logic.Party;

public class SoliderRepresentative extends Voter implements RepresentativeMarker, SoliderMarker {
	Representative rep;
	Solider solider;

	public SoliderRepresentative(Solider solider, Party underParty) {
		super(solider);
		rep = new Representative(solider, underParty);
		this.solider = solider;

	}

	@Override
	public Party getUnderParty() {
		return rep.getUnderParty();
	}

	@Override
	public void setUnderParty(Party underParty) {
		rep.setUnderParty(underParty);
	}

	@Override
	public boolean isCarryWeapon() {
		// TODO Auto-generated method stub
		return solider.isCarryWeapon();
	}

	@Override
	public void setCarryWeapon(boolean carryWeapon) {
		// TODO Auto-generated method stub
		solider.setCarryWeapon(carryWeapon);
	}

}
