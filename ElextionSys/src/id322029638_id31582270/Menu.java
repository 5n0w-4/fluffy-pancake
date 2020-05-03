package id322029638_id31582270;

import java.util.concurrent.Callable;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.Elections;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.logic.WING;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.Solider;
import id322029638_id31582270.population.Voter;
import menu.PartyPick;
import menu.YesNo;
import scannerWithMsg.ScannerWithMsg;
import set.Set;

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
				ScannerWithMsg.scanStr("enter your birth year please:"), yesNo("are you under quarantine?"));
		elections.addCitizen(tempCit);
	}

	public void addParty() {
		elections.addNewParty(ScannerWithMsg.scanStr("adding new party: \n enter name for your party please"),
				wingPick("choose wing direction for the party"));

	}

	public<T extends Voter> void addRepresentative() {
		T tempCit = elections.getCitizenById(ScannerWithMsg.scanStr("Please enter representative id:"));
		elections.setRepresentative(tempCit, partyPick());
	}

	public Party partyPick() {
		for (int i = 0; i < elections.getPartys().lenght(); i++) {
			if (elections.getPartys().get(i) instanceof Party) {
				Party temp = (Party) elections.getPartys().get(i);

				System.out.println((i + 1) + " " + temp.getName());
			}
		}
		return (Party) elections.getPartys().get(ScannerWithMsg.scanInt("") - 1);
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

//	public void voteCit() {
//		elections.vote(elections.getCitizenById(ScannerWithMsg.scanStr("Enter citizen id:")),
//				elections.getBBoxByAdress(ScannerWithMsg.scanStr("Enter ballot box adress:")),
//				partyPick());
//	}

	public void vote() throws Exception {
		PartyPick picker = new PartyPick();
		picker.load(elections.getPartys());

		YesNo isVoting = new YesNo();
		isVoting.load("Would you like to vote?");

		elections.getData(picker, isVoting);
		elections.countVotes();
	}

	public <T extends Voter> void startVoting() throws Exception {
		PartyPick picker = new PartyPick();
		picker.load(elections.getPartys());
		for (BBox<?> box : elections.getAllBoxes()) {
			if (box != null) {

				for (Voter subj : box.getAllowedToVoteHere()) {
					if (subj != null) {
						if (yesNo(subj.getName() + " Would you like to vote?")) {
							subj.setVoting(true);

							subj.vote(picker.call());
							;
						}

					}
				}
			}
		}
		elections.countVotes();
	}

}
