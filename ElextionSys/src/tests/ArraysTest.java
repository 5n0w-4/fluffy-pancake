package tests;

import org.junit.jupiter.api.Test;

import blocks.BBox;
import blocks.Citizen;
import blocks.Elections;
import blocks.Party;

public class ArraysTest {
	Elections elections = new Elections();
	
	@Test
	public void CitArrTest() {
		elections.addBBox("Place No A");
		for (int i = 0; i < 100; i++) {
			elections.addCitizen("John "+i, "i", "1990", false, true);
		}
	}
	
	@Test	
	public void PartyArrTest() {
		for (int i = 0; i < 100; i++) {
			elections.addNewParty("party "+i, blocks.WING.center);
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
		BBox tempBox = new BBox("Place No A");
		Party tempParty = new Party("party A", blocks.WING.center);
		for (int i = 0; i < 100; i++) {
			elections.vote(new Citizen("John "+i, "i", "1990", false, true), tempBox, tempParty);
		}
	}

}
