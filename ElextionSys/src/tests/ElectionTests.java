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

import id322029638_id31582270.BBox;
import id322029638_id31582270.Elections;
import id322029638_id31582270.Party;
import id322029638_id31582270.population.Citizen;

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
		elections.addCitizen("shlomy", "1", "1966", true, false, "derech shiba 2");
		elections.addCitizen("yossi", "2", "1959", false, true, "mivtza kadesh 38");
		elections.addCitizen("hagit", "3", "2001", false, true, "classified");
		elections.addCitizen("orly", "4", "1980", true, true, "derech shiba 2");
		elections.addCitizen("amir", "5", "2001", false, true, "classified");
		elections.addCitizen("moti", "6", "2002", false, true, "classified");

		// 6 Representatives
		elections.addCitizen("Bibi", "7", "1949", true, true, "derech shiba 2");
		elections.addCitizen("Gidon Saar", "8", "1966", false, true, "eben gabirol 35");
		elections.addCitizen("Beni Gantz", "9", "1959", false, true, "eben gabirol 35");
		elections.addCitizen("Gabi Ashkenazi", "10", "1954", false, true, "mivtza kadesh 38");
		elections.addCitizen("Avigdor Liberman", "11", "1958", false, true, "eben gabirol 35");
		elections.addCitizen("Amir Peretz", "12", "1952", false, true, "mivtza kadesh 38");

		// 4 Partys
		elections.addNewParty("Halicud", id322029638_id31582270.WING.right);
		elections.addNewParty("Kahol-lavan", id322029638_id31582270.WING.center);
		elections.addNewParty("IL our home", id322029638_id31582270.WING.right);
		elections.addNewParty("Haavoda", id322029638_id31582270.WING.left);

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
		assertTrue(elections.getPartys()[0] instanceof Party);
		assertTrue(elections.getAllBBox()[0] instanceof BBox);
		for (BBox bBox : elections.getAllBBox()) {
			if (bBox instanceof BBox) {
				assertTrue(bBox.getAllowedToVoteHere()[0] instanceof Citizen);
				assertTrue(bBox.getCastedVotes()[0] == null);
			}
		}
	}

	@Test
	public void testVote() {
		String temp = elections.vote(elections.getCitizenById("1"), elections.getCitizenById("1").getBBox(),
				elections.getPartyByName("Halicud"));
		elections.vote(elections.getCitizenById("2"), elections.getCitizenById("2").getBBox(),
				elections.getPartyByName("Halicud"));
		elections.vote(elections.getCitizenById("3"), elections.getCitizenById("3").getBBox(),
				elections.getPartyByName("Halicud"));
		elections.vote(elections.getCitizenById("4"), elections.getCitizenById("4").getBBox(),
				elections.getPartyByName("Halicud"));
		elections.vote(elections.getCitizenById("5"), elections.getCitizenById("5").getBBox(),
				elections.getPartyByName("Halicud"));
		elections.vote(elections.getCitizenById("6"), elections.getCitizenById("6").getBBox(),
				elections.getPartyByName("Halicud"));

		Scanner scan = new Scanner(elections.getBBoxByAdress("derech shiba 2").showRes(elections.getPartys()));
		scan.nextLine();
		assertEquals(2, scan.nextLine().charAt(17));
		assertTrue(temp.equals("Cant vote here"));
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
		for (BBox bBox : elections.getAllBBox()) {
			if (bBox instanceof BBox) {
				assertFalse(bBox.getAdress().isEmpty());
				assertEquals(bBox.getPercentageOfVotes(), 0);
				assertEquals(bBox.getNumOfCitizenWhoVote(), 3);
				assertEquals(bBox.getNumOfCitizenWhoVoteLogic(), 4);

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

	@Test
	public void checkCitizen() {
		for (BBox bBox : elections.getAllBBox()) {
			if (bBox instanceof BBox) {
				for (Citizen citizen : bBox.getAllowedToVoteHere()) {
					if (citizen instanceof Citizen) {
						assertFalse(citizen.getName().isEmpty());
						assertTrue(citizen.getAge() > 0 && citizen.getAge() < 100);
						assertTrue(Integer.parseInt(citizen.getId()) > 0);
						assertTrue(Integer.parseInt(citizen.getBirthYear()) > 1900);
						assertTrue(citizen.getVote() == null);
						assertTrue(citizen.getHealthStatus() == true || citizen.getHealthStatus() == false);
						assertTrue(citizen.getProtectionStatus() == true || citizen.getProtectionStatus() == false);
					}
				}
			}
		}
	}

}
