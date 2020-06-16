package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.logic.Party;

public class InfectedRepresentative extends Voter implements RepresentativeMarker, SickMarker {

	Representative rep;
	CoronoaPatient patient;

	public InfectedRepresentative(Voter subj, Party underParty) {
		super(subj);
		rep = new Representative(subj, underParty);
		patient = new CoronoaPatient(subj, 0);
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
	public int getDaysInfected() {
		// TODO Auto-generated method stub
		return patient.getDaysInfected();
	}

	@Override
	public void setDaysInfected(int daysInfected) {
		// TODO Auto-generated method stub
		patient.setDaysInfected(daysInfected);
	}

}
