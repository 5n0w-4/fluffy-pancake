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
		boolean ok = true;
		do {
			System.out.println("hi! welcome (again) to this period of ellections," + "\n"
					+ " please insert wich option you desire:");
			System.out.println("1- add ballot box");
			System.out.println("2- add citizen");
			System.out.println("3- add party");
			System.out.println("4- add nominee for specific party");
			System.out.println("5- show all ballot boxes details ");
			System.out.println("6- show all citizens details ");
			System.out.println("7- show all parties details");
			System.out.println("8- time to elect!");
			System.out.println("9- show results ");
			System.out.println("10- exit menu");
			System.out.println("\n" + "\n" + "\n");

			option = scan.next();

			switch (option) {

			case "1": // adding ballot box
			{
				System.out.println("are you interested adding corona Ballot Box, Army box, Regular?" + "\n"
						+ "c- for corona" + "\n" + "a- for army" + "\n" + "r- for Regular");
				check = scan.next().charAt(0);
				switch (check) {
				case 'c':
					System.out.println("enter BBox adress please:");
					temp = scan.next();
					elect.addNewBBox(1, temp);
					System.out.println("ballot box added succesfully, box id: " + elect.getBboxId(temp) + "\n");
					continue;
				case 'a':
					System.out.println("enter BBox adress please:");
					temp = scan.next();
					elect.addNewBBox(2, temp);
					System.out.println("ballot box added succesfully, box id: " + elect.getBboxId(temp) + "\n");
					continue;
				case 'r':
					System.out.println("enter BBox adress please:");
					temp = scan.next();
					elect.addNewBBox(0, temp);
					System.out.println("ballot box added succesfully, box id: " + elect.getBboxId(temp) + "\n");
					continue;
				}
			}
			case "2":// adding citizen
				System.out.println("adding Citizen to our system, step 1" + "\n" + "for new citizen enter 1" + "\n"
						+ "for details about exsicting citizen enter 2");
				check = scan.next().charAt(0);
				switch (check) {
				case '1':
					System.out.println("enter citizen name please:");
					System.out.println("enter citizen id please:");
					System.out.println("enter citizen birth year please:");
					System.out.println("are you under quarantine?" + "\n" + "1- no" + "\n" + "2- yes");

//					Citizen tempCit = new Citizen();
//					System.out.println("enter citizen name please:");
//					tempCit.setName(scan.next());
//					System.out.println("enter citizen id please:");
//					tempCit.setId(scan.nextInt());
//					System.out.println("enter citizen birth year please:");
//					tempCit.setBirthYear(scan.nextInt());
//					System.out.println("choose your party: " + "\n" + "1- halicud" + "\n" + "2-kahol lavan" + "\n" //option 4 is adding a party to citizen
//							+ "3-haavoda" + "\n" + "4-israel our home");
//					check = scan.next().charAt(0);
//					switch (check) {
//					case '1':
//						tempCit.setParty(partys[0]);
//						break;
//					case '2':
//						tempCit.setParty(partys[1]);
//						break;
//					case '3':
//						tempCit.setParty(partys[3]);
//						break;
//					case '4':
//						tempCit.setParty(partys[2]);
//						break;
//					}
//
//					System.out.println("are you under quarantine?" + "\n" + "1- no" + "\n" + "2- yes");
//					check = scan.next().charAt(0);
//					switch (check) {
//					case '1':
//						tempCit.setUnderQuarantine(false);
//						break;
//					case '2':
//						tempCit.setUnderQuarantine(true);
//						break;
//					}
//					if (elect.addCitizen(tempCit))
//						System.out.println("citizen added successfully!");
//					else
//						System.out.println("sorry we faced some problem adding you to our system  :(");
//					continue;
//				case '2':
//					System.out.println("enter citizen id please:");
//					elect.showOnesDetails(scan.nextInt());
//					continue;
				}

			case "3":// adding party

				System.out.println("adding new party:" + "\n" + " choose wing direction for your party please" + "\n"
						+ "1- right" + "\n" + "2- center" + "\n" + "3- left");
				check = scan.next().charAt(0);
				switch (check) {
				case '1':
					option = "right";
					break;
				case '2':
					option = "center";
					break;
				case '3':
					option = "left";
					break;
				}

				System.out.println();
				System.out.println("adding new party:" + "\n" + " enter name for your party please");

//				if (elect.addNewParty(scan.next(), option)) {
//					System.out.println("\n" + "party added successfully!" + "  party details:" + "\n"
//							+ elect.getPartys()[(elect.getNumOfPartys() - 1)].toString());
//					System.out.println();
//				} else {
//					System.out.println("oops something went wrong");
//				}
//				continue;

			case "4":// adding nominee
				System.out.println("Please enter representative id:");
				int tempId = scan.nextInt();
				Citizen[] tempPad = elect.getElectoralPad();
				int tempIndex = elect.findNominee(tempId);
				System.out.println("Please enter party name:"); //better to create a func that calls a menu which conststs of party options
				elect.setRepresentative(tempPad[tempIndex], scan.nextLine());

//				System.out
//						.println("hi, here you can add yourself to our nominies list" + "\n" + "please enter your id:");
//				int tempId = scan.nextInt();
//				if (elect.findNominee(tempId) >= 0) {
//					int tempIndex = elect.findNominee(tempId);
//					System.out.println(elect.getElectoralPad()[tempIndex].toString());
//					System.out.println("\n" + "\n" + "hello again! please confirn your details:" + "\n" + "1- confirm"
//							+ "\n" + "2- not my details");
//					check = scan.next().charAt(0);
//					switch (check) {
//					case '1':
//
//						if (elect.getElectoralPad()[tempIndex].getUnderParty()
//								.addNominee(elect.getElectoralPad()[tempIndex]))
//							System.out.println("great, Citizen id: " + tempId + " has been added to "
//									+ elect.getElectoralPad()[tempIndex].getUnderParty().getName() + " nominies list");
//						break;
//					case '2':
//						System.out.println("sorry, please try again");
//						break;
//					}
//				} else {
//					System.out.println(
//							"your id is not on our system," + "\n" + "please add yourself to our system first.");
//				}
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
