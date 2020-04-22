package blocks;

public class Menu {
	Elections elections;

	public Menu(Elections elections) {
		this.elections = elections;
	}

	public void printMenu() {
		System.out.println("hi! welcome to elections" + elections.getDate() + "to this period of ellections," + "\n"
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
	}

	public void addBBox() {
		switch (ScannerWithMsg.scanInt("1- for corona \n2- for army \n0- for Regular")) {
		case 1:
			elections.addCoronaBox(ScannerWithMsg.scanStr("Enter the location of the ballot box:"));
			break;

		case 2:
			elections.addArmyBox(ScannerWithMsg.scanStr("Enter the location of the ballot box:"));
			break;

		case 0:
			elections.addBBox(ScannerWithMsg.scanStr("Enter the location of the ballot box:"));
			break;
		}
	}

	public void addCitizen() {// redundant lines ---> can insert it all to addCitizen , should I??
		Citizen tempCit = new Citizen(ScannerWithMsg.scanStr("enter citizen name please:"),
				ScannerWithMsg.scanStr("enter citizen id please:"),
				ScannerWithMsg.scanStr("enter your birth year please:"), yesNo("are you under quarantine?"),
				yesNo("Do you have hazmat suit?"));
		elections.addCitizen(tempCit);
	}

	public void addParty() {
		elections.addNewParty(ScannerWithMsg.scanStr("adding new party: \n enter name for your party please"),
				wingPick("choose wing direction for the party"));

	}

	public void addRepresentative() {
		Citizen tempCit = elections.getCitizenById(ScannerWithMsg.scanStr("Please enter representative id:"));
		elections.setRepresentative(tempCit, partyPick());
	}

	public Party partyPick() {
		for (int i = 0; i < elections.getPartys().length; i++) {
			if (elections.getPartys()[i] instanceof Party) {

				System.out.println((i + 1) + " " + elections.getPartys()[i].getName());
			}
		}
		return elections.getPartys()[ScannerWithMsg.scanInt("") - 1];
	}

	public WING wingPick(String msg) {
		System.out.println(msg);
		for (int i = 0; i < WING.values().length; i++) {
			System.out.println((i + 1) + " " + WING.values()[i].toString());
		}
		return WING.values()[ScannerWithMsg.scanInt("") - 1];
	}

	public void printAllBBox() {
		System.out.println(elections.showAllBBox());
	}

	public void printAllCitizen() {
		System.out.println(elections.showAllVoters());
	}

	public void printAllPartys() {
		System.out.println(elections.showAllPartys());
	}

	public void printElectionResult() {
		System.out.println(elections.showRes());
	}

	public boolean yesNo(String msg) {
		System.out.println(msg);
		if (ScannerWithMsg.scanInt("1-Yes \n 2-No") == 1) {
			return true;
		} else
			return false;

	}

	public void voteCit() {
		elections.vote(elections.getCitizenById(ScannerWithMsg.scanStr("Enter citizen id:")),
				elections.getBBoxByAdress(ScannerWithMsg.scanStr("Enter ballot box adress:")),
				partyPick());
	}

	public void voteAll() {
		for (BBox bBox : elections.getAllBBox()) {
			if (bBox instanceof BBox) {
				if (bBox instanceof CoronaBox) 
					bBox = (CoronaBox)bBox;
				if (bBox instanceof ArmyBox) 
					bBox = (ArmyBox)bBox;

				for (Citizen citizen : bBox.getAllowedToVoteHere()) {
					if (citizen instanceof Citizen) {

						if (yesNo(citizen.getName() + " Would you like to vote?")) {
							elections.vote(citizen, bBox, partyPick());
						}
					}
				}
			}
		}
	}
}
