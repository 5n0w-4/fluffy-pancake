package blocks;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String option, temp;
		char check;

		Elections elect = new Elections();// starting elections season\
		// hard coding of few citizens, parties, and bbox
		// parties
		Party[] partys = new Party[4];
		partys[0] = new Party("Halicud", "right");
		partys[1] = new Party("Kahol-lavan", "center");
		partys[2] = new Party("israel our home", "right");
		partys[3] = new Party("Haavoda", "left");

		// citizens
		Citizen[] testCit = new Citizen[6];
		testCit[0] = new Citizen("shlomy", 1, 1966, true, true);
		testCit[1] = new Citizen("yossi", 2, 1959, false, true);
		testCit[2] = new Citizen("hagit", 3, 1999, false, true);
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

//		BBox northTlv = new BBox("mivtza kadesh 38");
//		BBox centerTlv = new BBox("eben gabirol 35");
//		BBox southTlv = new BBox("hkovshim 28");
//		CoronaBox Shiba = new CoronaBox("derech shiba 2");
//		ArmyBox Hakiria = new ArmyBox("classified");

		elect.addNewBBox(0, "mivtza kadesh 38");
		elect.addNewBBox(0, "eben gabirol 35");
		elect.addNewBBox(0, "hkovshim 28");
		elect.addNewBBox(1, "derech shiba 2");
		elect.addNewBBox(2, "classified");
		
		
		for (Party party : partys) {
			elect.addNewParty(party);
		}
		for (Citizen citizen : testCit) {
			elect.addCitizen(citizen);
		}

		elect.setRepresentative(testNom2[0], "Halicud");
		elect.setRepresentative(testNom2[1], "Halicud");
		elect.setRepresentative(testNom2[2], "Kahol-lavan");
		elect.setRepresentative(testNom2[3], "Kahol-lavan");
		elect.setRepresentative(testNom2[4], "israel our home");
		elect.setRepresentative(testNom2[5], "Haavoda");

	

		// adding citizens
//		elect.addCitizenGroup(testCit);
//		elect.addCitizenGroup(testNom2);
		/*
		 * System.out.println(elect.addCitizenGroup(test));
		 * System.out.println(elect.addCitizenGroup(test2));
		 * System.out.println(elect.showAllVoters());
		 */
		Menu menu = new Menu(elect);
		boolean ok = true;
		do {
			menu.printMenu();
			option = scan.next();

			switch (option) {
			case "1": // adding ballot box
			{
				break;
			}
			
			case "2":// adding citizen
			{
				menu.addCitizen();
				break;
			}
				
			case "3":// adding party
			{
				menu.addParty();
				break;
			}
			case "4":// adding nominee
				System.out.println("Please enter representative id:");
				int tempId = scan.nextInt();
				Citizen[] tempPad = elect.getElectoralPad();
				int tempIndex = elect.findNominee(tempId);
				System.out.println("Please enter party name:"); //better to create a func that calls a menu which conststs of party options
				elect.setRepresentative(tempPad[tempIndex], scan.nextLine());
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
				
				elect.vote(scan);
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
