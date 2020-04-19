package blocks;

import java.util.Scanner;

import blocks.Party.WING;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String option, temp;
		char check;

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
		testCit[0] = new Citizen("shlomy", 1, 1966, true, true);
		testCit[1] = new Citizen("yossi", 2, 1959, false, true);
		testCit[2] = new Citizen("hagit", 3, 2001, false, true);
		testCit[3] = new Citizen("orly", 4, 1980, true, true);
		testCit[4] = new Citizen("amir", 5, 1990, false, true);
		testCit[5] = new Citizen("moti", 6, 1975, false, true);

		// nominees
		Citizen[] testNom2 = new Citizen[6];
		testNom2[0] = new Citizen("Bibi", 7, 1949, false, true);
		testNom2[1] = new Citizen("Gidon Saar", 8, 1966, false, true);
		testNom2[2] = new Citizen("Beni Gantz", 9, 1959, false, true);
		testNom2[3] = new Citizen("Gabi Ashkenazi", 10, 1954, false, true);
		testNom2[4] = new Citizen("Avigdor Liberman", 11, 1958, false, true);
		testNom2[5] = new Citizen("Amir Peretz", 12, 1952, false, true);

		// Ballot boxes
		elect.addBBox("mivtza kadesh 38");
		elect.addBBox("eben gabirol 35");
		elect.addBBox("hkovshim 28");
		elect.addCoronaBox("derech shiba 2");
		elect.addArmyBox("classified");


		for (Party party : partys) {
			elect.addNewParty(party);
		}
		for (Citizen citizen : testCit) {
			elect.addCitizen(citizen);
		}

		elect.setRepresentative(testNom2[0], partys[0]);

		elect.setRepresentative(testNom2[5], partys[3]);

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

				menu.addCitizen();
				break;

			case "3":// adding party

				menu.addParty();
				break;

			case "4":// adding nominee
				menu.addRepresentative();
				break;
			case "5":// show all BBox details
				System.out.println(elect.showAllBBox());
				break;
			case "6":// show all citizens details
				System.out.println(elect.showAllVoters()); // Create "Voter" class that extends Citizen? *if cit is
															// voting than cast to voter*
				break;
			case "7":// show all parties details
				System.out.println(elect.showAllPartys());
				break;
			case "8":// electing

				menu.voteAll();
				break;
			case "9":// show results
				System.out.println(elect.showRes());

				break;
			case "10":
				ok = false;
				break;
			}

		} while (ok != false);

	}

}
