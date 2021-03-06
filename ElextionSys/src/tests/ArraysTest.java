package tests;

import org.junit.jupiter.api.Test;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.Elections;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Citizen;

public class ArraysTest {
	Elections elections = new Elections();
	
	@Test
	public void CitArrTest() {
		elections.addBBox("Place No A");
		for (int i = 0; i < 100; i++) {
			elections.addCitizen("John "+i, "i", "1990", false);
			if (i == 90) {
				elections.addCitizen("John "+i, "i", "1990", false);
			}
		}
	}
	
	@Test	
	public void PartyArrTest() {
		for (int i = 0; i < 100; i++) {
			elections.addNewParty("party "+i, id322029638_id31582270.logic.WING.center);
		}
	}
	
	@Test
	public void BBoxArrTest() {
		for (int i = 0; i < 100; i++) {
			elections.addBBox("Place No "+i);
			elections.addCoronaBox("Place No "+i+1);
			elections.addArmyBox("Place No "+i+2);
		}
	}
	
	@Test
	public void CastedVotesTest() {
		elections.addBBox("Place No A");
		elections.addNewParty("party A", id322029638_id31582270.logic.WING.center);
		for (int i = 0; i < 100; i++) {
			Citizen temp = new Citizen("John "+i, "i", "1990", false);
			elections.addCitizen("John "+i, "i", "1990", false);
			
		}
	}

}
