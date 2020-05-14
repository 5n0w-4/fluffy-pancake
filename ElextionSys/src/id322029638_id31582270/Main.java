//Ivan Kalinovski - 322029638
//Yoav - 315822270

package id322029638_id31582270;

import java.util.Scanner;

import exceptions.Invalid_Age;
import exceptions.Invalid_Id;
import id322029638_id31582270.logic.Elections;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.logic.WING;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.Voter;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String option;

		Elections elect = new Elections();// starting elections season\
		// hard coding of few citizens, parties, and bbox
		// parties
		Party[] partys = new Party[4];
		partys[0] = new Party("Halicud", WING.right);
		partys[1] = new Party("Kahol-lavan", WING.center);
		partys[2] = new Party("IL our home", WING.right);
		partys[3] = new Party("Haavoda", WING.left);

		// citizens
		Citizen[] testCit = new Citizen[6];
		testCit[0] = new Citizen("shlomy", "1", "1966", true);
		testCit[1] = new Citizen("yossi", "2", "1959", false);
		testCit[2] = new Citizen("hagit", "3", "2001", false);
		testCit[3] = new Citizen("orly", "4", "1980", true);
		testCit[4] = new Citizen("amir", "5", "1990", false);
		testCit[5] = new Citizen("moti", "6", "1975", false);

		// nominees
		Citizen[] testNom2 = new Citizen[6];
		testNom2[0] = new Citizen("Bibi", "7", "1949", false);
		testNom2[1] = new Citizen("Gidon Saar", "8", "1966", false);
		testNom2[2] = new Citizen("Beni Gantz", "9", "1959", false);
		testNom2[3] = new Citizen("Gabi Ashkenazi", "10", "1954", false);
		testNom2[4] = new Citizen("Avigdor Liberman", "11", "1958", false);
		testNom2[5] = new Citizen("Amir Peretz", "12", "1952", false);

		// Ballot boxes
		elect.addBBox("mivtza kadesh 38");
		elect.addBBox("eben gabirol 35");
		elect.addBBox("hkovshim 28");
//		elect.addCoronaBox("derech shiba 2");
		elect.addArmyBox("classified");

		for (Party party : partys) {
			elect.addNewParty(party);
		}
		for (Citizen citizen : testCit) {
			elect.addCitizen(citizen);
		}

		elect.setRepresentative(new Voter(elect.getCitizenById("2"), true, true), partys[0]);

		elect.setRepresentative(new Voter(elect.getCitizenById("3"), true, true), partys[3]);

		Menu menu = new Menu(elect);
		boolean ok = true;
		do {
			menu.printMenu();
			option = scan.next();

			switch (option) {
			case "1": // adding ballot box
				menu.addBBox();
				break;

			case "2":// adding citizen
				try {
					menu.addCitizen();
					break;
				} catch (Invalid_Age e) {
					System.out.println(
							"Citizen is " + (2020 - Integer.parseInt(e.getAge()) + " years old. He can't vote yet."));
					break;
				} catch (Invalid_Id e) {
					System.out.println(e.getId() + " is invalid./n ID must contain 9 symbols and consist of 0-9.");
					break;
				}

			case "3":// adding party
				menu.addParty();
				break;

			case "4":// adding nominee
				menu.addRepresentative();
				break;

			case "5":// show all BBox details
				menu.printAllBBox();
				break;

			case "6":// show all citizens details
				menu.printAllCitizen();
				break;

			case "7":// show all parties details
				menu.printAllPartys();
				break;

			case "8":// electing
				menu.vote();
				break;

			case "9":// show results
				menu.printElectionResult();
				break;

			case "10":
				ok = false;
				scan.close();
				break;
			}

		} while (ok != false);

	}

}
