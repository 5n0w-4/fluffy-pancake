package id322029638_id31582270.population;

import id322029638_id31582270.exceptions.Invalid_Age;
import id322029638_id31582270.exceptions.Invalid_Id;
import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.logic.Party;

public class InfectedRepresentative extends Representative implements RepresentativeMarker,SickMarker{
	private int daysInfected;

	

	public InfectedRepresentative(Representative rep, Party underParty, int daysInfected) throws Invalid_Age, Invalid_Id {
		super(rep);
		this.daysInfected = daysInfected;
	}
	@Override
	public int getDaysInfected() {
		return daysInfected;
	}
@Override
	public void setDaysInfected(int daysInfected) {
		this.daysInfected = daysInfected;
	}

}
