package blocks;

import java.util.Scanner;

public class Menu {
	Elections elections;

	public Menu(Elections elections) {
		this.elections = elections;
	}

	public void printMenu() {
		System.out.println(
				"hi! welcome (again) to this period of ellections," + "\n" + " please insert wich option you desire:");
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
	}

	public void addBBox(Scanner scan) {
		int choice;
		String adress;
		System.out.println("are you interested adding corona Ballot Box, Army box, Regular?" + "\n" + "1- for corona"
				+ "\n" + "2- for army" + "\n" + "0- for Regular");
		choice = scan.nextInt();
		System.out.println("enter BBox adress please:");
		adress = scan.next();
		elections.addNewBBox(choice, adress);
		System.out.println("ballot box added succesfully, box id: " + elections.getBboxId(adress) + "\n");

	}

	public void addCitizen() {
		Citizen tempCit = new Citizen(ScannerWithMsg.scanStr("enter citizen name please:"),
				ScannerWithMsg.scanInt("enter citizen id please:"),
				ScannerWithMsg.scanInt("enter citizen birth year please:"),
				ScannerWithMsg.scanBool("are you under quarantine?" + "\n" + "1- no" + "\n" + "2- yes"),
				ScannerWithMsg.scanBool("Do you have hazmat suit?"));
		elections.addCitizen(tempCit);
	}
}
