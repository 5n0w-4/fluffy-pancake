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
		Party Halicud = new Party("Halicud", "right");
		Party Kahol = new Party("Kahol-lavan", "center");
		Party Home = new Party("israel our home", "right");
		Party Haavoda = new Party("Haavoda", "left");

		// citizens
		Citizen[] test = new Citizen[6];
		test[0] = new Citizen("shlomy", 1, 1966, Kahol, true, true, true);
		test[1] = new Citizen("yossi", 2, 1959, Halicud, false, true, true);
		test[2] = new Citizen("hagit", 3, 1987, Kahol, false, true, true);
		test[3] = new Citizen("orly", 4, 1980, Home, true, true, true);
		test[4] = new Citizen("amir", 5, 1990, Haavoda, false, true, true);
		test[5] = new Citizen("moti", 6, 1975, Halicud, false, true, false);

		// nominees
		Citizen nominee0 = new Citizen("Bibi", 7, 1949, Halicud, false, true, true);
		Citizen nominee1 = new Citizen("Gidon Saar", 8, 1966, Halicud, false, true, true);

		Citizen nominee2 = new Citizen("Beni Gantz", 9, 1959, Kahol, false, true, true);
		Citizen nominee3 = new Citizen("Gabi Ashkenazi", 10, 1954, Kahol, false, true, true);

		Citizen nominee4 = new Citizen("Avigdor Liberman", 11, 1958, Home, false, true, true);

		Citizen niminee5 = new Citizen("Amir Peretz", 12, 1952, Haavoda, false, true, true);
		// Ballot boxes

		BBox northTlv = new BBox("mivtza kadesh 38");
		BBox centerTlv = new BBox("eben gabirol 35");
		BBox southTlv = new BBox("hkovshim 28");
		CoronaBox Shiba = new CoronaBox("derech shiba 2");
		ArmyBox Hakiria = new ArmyBox("classified");

		elect.addNewBBox(0, northTlv);
		elect.addNewBBox(0, centerTlv);
		elect.addNewBBox(0, southTlv);
		elect.addNewBBox(1, Shiba);
		elect.addNewBBox(2, Hakiria);

		// adding citizens
		System.out.println(elect.addCitizenGroup(test));

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
					Citizen tempCit = new Citizen();
					System.out.println("enter citizen name please:");
					tempCit.setName(scan.next());
					System.out.println("enter citizen id please:");
					tempCit.setId(scan.nextInt());
					System.out.println("enter citizen birth year please:");
					tempCit.setBirthYear(scan.nextInt());
					System.out.println("choose your party: " + "\n" + "1- halicud" + "\n" + "2-kahol lavan" + "\n"
							+ "3-haavoda" + "\n" + "4-israel our home");
					check = scan.next().charAt(0);
					switch (check) {
					case '1':
						tempCit.setParty(Halicud);
						break;
					case '2':
						tempCit.setParty(Kahol);
						break;
					case '3':
						tempCit.setParty(Haavoda);
						break;
					case '4':
						tempCit.setParty(Home);
						break;
					}

					System.out.println("are you under quarantine?" + "\n" + "1- no" + "\n" + "2- yes");
					check = scan.next().charAt(0);
					switch (check) {
					case '1':
						tempCit.setUnderQuarantine(false);
						break;
					case '2':
						tempCit.setUnderQuarantine(true);
						break;
					}
					if (elect.addCitizen(tempCit))
						System.out.println("citizen added successfully!");
					else
						System.out.println("sorry we faced some problem adding you to our system  :(");
					continue;
				case '2':
					System.out.println("enter citizen id please:");
					elect.showOnesDetails(scan.nextInt());
					continue;
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
					
					if(elect.addNewParty(scan.next(), option)) {
						System.out.println("\n"+"party added successfully!"+"  party details:"+"\n"+elect.getPartys()[(elect.getNumOfPartys()-1)].toString());
					System.out.println();}
					else {
						System.out.println("oops something went wrong");
					}
					continue;
			
			case "4":// adding nominee

				break;
			case "5":// show all BBox details

				break;
			case "6":// show all citizens details

				break;
			case "7":// show all parties details

				break;
			case "8":// electing

				break;
			case "9":// show results

				break;
			case "10":
				ok = false;
				break;
			}

		} while (ok != false);

	}

}
