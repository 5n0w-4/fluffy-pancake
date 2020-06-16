package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;
import id322029638_id31582270.logic.Party;

public class InfectedSoliderRepresentative extends InfectedSolider implements RepresentativeMarker{

	Representative rep;

	public InfectedSoliderRepresentative(InfectedSolider subj, Party underParty) {
		super(subj);
		rep = new Representative(subj, underParty);
	}

	@Override
	public Party getUnderParty() {
		// TODO Auto-generated method stub
		return rep.getUnderParty();
	}

	@Override
	public void setUnderParty(Party underParty) {
		rep.setUnderParty(underParty);
	}

}
