package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.Elections;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.Voter;

public class ElectionTests {
	Elections elections = new Elections();

	public ElectionTests() {

	}

	@BeforeEach
	void setUp() throws Exception {

		// 5 BBox
		elections.addBBox("mivtza kadesh 38");
		elections.addBBox("eben gabirol 35");
		elections.addCoronaBox("derech shiba 2");
		elections.addArmyBox("classified");

		// 6 Citizen
//		elections.addCitizen("shlomy", "1", "1966", true, false, "derech shiba 2");
//		elections.addCitizen("yossi", "2", "1959", false, true, "mivtza kadesh 38");
//		elections.addCitizen("hagit", "3", "2001", false, true, "classified");
//		elections.addCitizen("orly", "4", "1980", true, true, "derech shiba 2");
//		elections.addCitizen("amir", "5", "2001", false, true, "classified");
//		elections.addCitizen("moti", "6", "2002", false, true, "classified");
//
//		// 6 Representatives
//		elections.addCitizen("Bibi", "7", "1949", true, true, "derech shiba 2");
//		elections.addCitizen("Gidon Saar", "8", "1966", false, true, "eben gabirol 35");
//		elections.addCitizen("Beni Gantz", "9", "1959", false, true, "eben gabirol 35");
//		elections.addCitizen("Gabi Ashkenazi", "10", "1954", false, true, "mivtza kadesh 38");
//		elections.addCitizen("Avigdor Liberman", "11", "1958", false, true, "eben gabirol 35");
//		elections.addCitizen("Amir Peretz", "12", "1952", false, true, "mivtza kadesh 38");
		
		//Without adress ----> for referance check older versions ----> will be fixed later
		elections.addCitizen("shlomy", "1", "1966", true);
		elections.addCitizen("yossi", "2", "1959", false);
		elections.addCitizen("hagit", "3", "2001", false);
		elections.addCitizen("orly", "4", "1980", true);
		elections.addCitizen("amir", "5", "2001", false);
		elections.addCitizen("moti", "6", "2002", false);
	

		// 6 Representatives
		elections.addCitizen("Bibi", "7", "1949", true);
		elections.addCitizen("Gidon Saar", "8", "1966", false);
		elections.addCitizen("Beni Gantz", "9", "1959", false);
		elections.addCitizen("Gabi Ashkenazi", "10", "1954", false);
		elections.addCitizen("Avigdor Liberman", "11", "1958", false);
		elections.addCitizen("Amir Peretz", "12", "1952", false);

		// 4 Partys
		elections.addNewParty("Halicud", id322029638_id31582270.logic.WING.right);
		elections.addNewParty("Kahol-lavan", id322029638_id31582270.logic.WING.center);
		elections.addNewParty("IL our home", id322029638_id31582270.logic.WING.right);
		elections.addNewParty("Haavoda", id322029638_id31582270.logic.WING.left);
		
		elections.getCitizenById("1").setMyVote(elections.getPartyByName("Halicud"));
		elections.getCitizenById("2").setMyVote(elections.getPartyByName("Halicud"));
		elections.getCitizenById("3").setMyVote(elections.getPartyByName("Halicud"));
		elections.getCitizenById("4").setMyVote(elections.getPartyByName("Halicud"));
		elections.getCitizenById("5").setMyVote(elections.getPartyByName("Halicud"));
		elections.getCitizenById("6").setMyVote(elections.getPartyByName("Halicud"));

		// Add the representatives
		System.out.println(elections.getCitizenById("7"));
		elections.setRepresentative(elections.getCitizenById("7"), elections.getPartyByName("Halicud"));
		elections.setRepresentative(elections.getCitizenById("8"), elections.getPartyByName("Halicud"));
		elections.setRepresentative(elections.getCitizenById("9"), elections.getPartyByName("Kahol-lavan"));
		elections.setRepresentative(elections.getCitizenById("10"), elections.getPartyByName("Kahol-lavan"));
		elections.setRepresentative(elections.getCitizenById("11"), elections.getPartyByName("IL our home"));
		elections.setRepresentative(elections.getCitizenById("5"), elections.getPartyByName("IL our home"));
		elections.setRepresentative(elections.getCitizenById("12"), elections.getPartyByName("Haavoda"));
		elections.setRepresentative(elections.getCitizenById("4"), elections.getPartyByName("Haavoda"));

		System.out.println(elections.showAllBBox());
		System.out.println(elections.showAllPartys());
		System.out.println(elections.showAllVoters());
		System.out.println(elections.showRes());

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void checkArrays() {// contains at least 1 element
		assertTrue(elections.getPartys().get(0) instanceof Party);
		assertTrue(elections.getAllBoxes().get(0) instanceof BBox);
		for (BBox bBox : elections.getAllBoxes()) {
			if (bBox instanceof BBox) {
				assertTrue(bBox.getAllowedToVoteHere().get(0) instanceof Citizen);
				assertTrue(bBox.getCastedVotes()[0] == null);
			}
		}
	}

	@Test
	public void testVote() {
		elections.vote();
		CoronoaPatient tempVal = new CoronoaPatient(elections.getCitizenById("3"), 0);
		Scanner scan = new Scanner(elections.getBox(tempVal).showRes(elections.getPartys()));
		scan.nextLine();
		assertEquals(2, scan.nextLine().charAt(17));
//		assertTrue(temp.equals("Cant vote here"));
	}

	@Test
	public void checkPartys() {
		for (Party party : elections.getPartys()) {
			assertFalse(party.getName().isEmpty());
			assertTrue(party.getWingDirect() != null);
			assertTrue(party.getCreationDate() instanceof LocalDate);
			assertEquals(party.getNumOfRepresentatives(), 2);
			assertEquals(party.getNumOfRepresentativesLogic(), 2);
			assertTrue(party.getRepresentatives()[0] instanceof Citizen);
			assertTrue(party.getWingDirect() != null);
		}
	}

	@Test
	public void checkBBox() {
		for (BBox<?> bBox : elections.getAllBoxes()) {
			if (bBox instanceof BBox) {
				assertFalse(bBox.getAdress().isEmpty());
				assertEquals(bBox.getNumOfCitizenWhoVote(), 3);

			}
		}
	}

	@Test
	public void checkRepresentatives() {
		String[] expectedOut1 = { "Bibi", "Gidon Saar" };
		String[] expectedOut2 = { "Beni Gantz", "Gabi Ashkenazi" };
		String[] expectedOut3 = { "Avigdor Liberman", "amir" };
		String[] expectedOut4 = { "Amir Peretz", "orly" };
		assertArrayEquals(elections.getPartyByName("Halicud").getRepNames(), expectedOut1);
		assertArrayEquals(elections.getPartyByName("Kahol-lavan").getRepNames(), expectedOut2);
		assertArrayEquals(elections.getPartyByName("IL our home").getRepNames(), expectedOut3);
		assertArrayEquals(elections.getPartyByName("Haavoda").getRepNames(), expectedOut4);

	}

//	@Test
//	public<T extends Voter> void checkCitizen() {
//		for (BBox<?> bBox : elections.getAllBoxes()) {
//			if (bBox instanceof BBox) {
//				bBox = (BBox<T>) bBox;
//				for (T citizen : bBox.getAllowedToVoteHere()) {
//					if (citizen instanceof Citizen) {
//						assertFalse(citizen.getName().isEmpty());
//						assertTrue(citizen.getAge() > 0 && citizen.getAge() < 100);
//						assertTrue(Integer.parseInt(citizen.getId()) > 0);
//						assertTrue(Integer.parseInt(citizen.getBirthYear()) > 1900);
//
//					}
//				}
//			}
//		}
//	}

}
