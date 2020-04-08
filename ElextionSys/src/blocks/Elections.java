package blocks;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Elections {
	private LocalDate date;
	private Citizen[] electoralPad;
	private Party[] partys;
	private BBox[] ballotBoxes;
	private int numOfCitizen;
	private int numOfBBOx;
	private int numOfPartys;
	private int numOfCitizenLogic;

	public Citizen[] getElectoralPad() {
		return electoralPad;
	}

	private int numOfBBOxLogic;
	private int numOfPartysLogic;

	public Elections() {
		this.date = LocalDate.now();
		this.numOfCitizen = 0;
		this.numOfBBOx = 0;
		this.numOfPartys = 0;
		this.numOfCitizenLogic = 1000;
		this.numOfBBOxLogic = 10;
		this.numOfPartysLogic = 5;
		this.electoralPad = new Citizen[numOfCitizenLogic];
		this.partys = new Party[numOfPartysLogic];
		this.ballotBoxes = new BBox[numOfBBOxLogic];
//		startPartys();
//		startBoxes();
//		startPad();
	}

	public Elections(Elections copy) {
		this.electoralPad = copy.electoralPad;
		this.partys = copy.partys;
		this.ballotBoxes = copy.ballotBoxes;
		this.numOfCitizen = copy.numOfCitizen;
		this.numOfBBOx = copy.numOfBBOx;
		this.numOfPartys = copy.numOfPartys;
		this.numOfCitizenLogic = copy.numOfCitizenLogic;
		this.numOfPartysLogic = copy.numOfPartysLogic;
		this.numOfBBOxLogic = copy.numOfBBOxLogic;
	}

	public void startPad() {
		for (int i = 1; i < 6; i++) {
			Citizen temp = addCitizen("John " + i, i * 11111111, 1990 + i, null, false, false);
		}
	}

	public void startPartys() {
		for (int i = 0; i < 3; i++) {
			addNewParty("Party " + i, "");
		}
	}

	public void startBoxes() {
		addNewBBox(1, "123.St");
		addNewBBox(2, "234.St");
		addNewBBox(0, "345.St");
	}

	public Voter makeVoter(Citizen citizen) {  //maybe to remove
		return new Voter(citizen);
	}

	public void vote(Scanner scan) {
		boolean isVoting;
		for (BBox bBox : ballotBoxes) {
			if (bBox != null) {

				for (Citizen citizen : bBox.getAllowedToVoteHere()) {
					if (citizen != null) {

						System.out.println(citizen.getName() + " Would you like to vote?	");
						isVoting = scan.nextBoolean();
						scan.nextLine();
						if (isVoting) {
							Voter temp = new Voter(citizen);
							System.out.println("To whom would you like to vote?");
							String voteTo = scan.nextLine();
							Party voteToParty = search(voteTo);
							temp.Vote(voteToParty);
							bBox.vote(temp);
							citizen = temp;
						}
					}
				}

			}

		}
	}

	public String showRes() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			if (bBox != null) {
				buf.append("Results in the ballot box on " + bBox.getAdress() + "St. \n");
				buf.append(bBox.showRes(partys) + "\n");
			}
		}
		return buf.toString();
	}

	public Party search(String dest) {
		for (Party party : partys) {
			if (party != null) {
				if (party.compareName(dest) == 0) {
					return party;
				}
			}
		}
		return null;
	}

	public boolean checkCapacity(int size, int sizeLogic) { // Q:Do we have room?
		if (!(size >= sizeLogic)) {
			return true;
		}
		return false;
	}

	public int getBboxId(String adress) {
		for (int i = 0; i < ballotBoxes.length; i++)
			if (ballotBoxes[i].getAdress().equals(adress))
				return ballotBoxes[i].getId();
		return 0;
	}

	public BBox getBox(int type) { // coronaBox 1 armyBox 2 bBox 0
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof CoronaBox && type == 1) {
				return bBox;
			}
			if (bBox instanceof ArmyBox && type == 2) {
				return bBox;
			}
			if (bBox instanceof BBox && type == 0) {
				return bBox;
			}
		}
		return null;
	}

	public void addNewParty(String name, String wing) {
		if (checkCapacity(numOfPartys, numOfPartysLogic)) {
			partys[numOfPartys] = new Party(name, wing);
			numOfPartys++;
		} else {
			numOfPartysLogic *= 2;
			partys = Arrays.copyOf(partys, numOfPartysLogic);
			addNewParty(name, wing);
		}
	}

	public void addNewParty(Party other) {
		if (checkCapacity(numOfPartys, numOfPartysLogic)) {
			partys[numOfPartys] = new Party(other);
			numOfPartys++;
		} else {
			numOfPartysLogic *= 2;
			partys = Arrays.copyOf(partys, numOfPartysLogic);
			addNewParty(other);
		}
	}

	public int getNumOfPartys() {
		return numOfPartys;
	}

	public Party[] getPartys() {
		return partys;
	}

	public String showAllBBox() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			if (bBox != null) {

				buf.append(bBox.toString() + "\n");
			}
		}
		return buf.toString();
	}

	public String showAllPartys() {
		StringBuffer buf = new StringBuffer();
		for (Party party : partys) {
			if (party != null) {

				buf.append(party.toString());
			}
		}
		return buf.toString();
	}

	public String showAllVoters() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox) {

				buf.append(bBox.showVoters() + "\n");
			}
		}
		return buf.toString();
	}

	public void setRepresentative(Citizen subj, String partyName) {
		Party temp = search(partyName);
		temp.addRep(subj);

	}

	public void addNewBBox(int type, String adress) { // coronaBox 1 armyBox 2 bBox 0
		if (checkCapacity(numOfBBOx, numOfBBOxLogic)) {

			switch (type) {
			case 1:
				CoronaBox cBox = new CoronaBox(adress);
				ballotBoxes[numOfBBOx] = cBox;
				break;
			case 2:
				ArmyBox aBox = new ArmyBox(adress);
				ballotBoxes[numOfBBOx] = aBox;
				break;
			case 0:
				BBox bBox = new BBox(adress);
				ballotBoxes[numOfBBOx] = bBox;
				break;
			}
			numOfBBOx++;
		} else {
			numOfBBOxLogic *= 2;// working with this ref so didnt create a func
			ballotBoxes = Arrays.copyOf(ballotBoxes, numOfBBOxLogic);
			addNewBBox(type, adress);
		}
	}

	public void addNewBBox(int type, BBox other) { // coronaBox 1 armyBox 2 bBox 0
		if (checkCapacity(numOfBBOx, numOfBBOxLogic)) {

			switch (type) {
			case 1:
				CoronaBox cBox = new CoronaBox(other.getAdress());
				ballotBoxes[numOfBBOx] = cBox;
				break;
			case 2:
				ArmyBox aBox = new ArmyBox(other.getAdress());
				ballotBoxes[numOfBBOx] = aBox;
				break;
			case 0:
				BBox bBox = new BBox(other.getAdress());
				ballotBoxes[numOfBBOx] = bBox;
				break;
			}
			numOfBBOx++;
		} else {
			numOfBBOxLogic *= 2;// working with this ref so didnt create a func
			ballotBoxes = Arrays.copyOf(ballotBoxes, numOfBBOxLogic);
			addNewBBox(type, other.getAdress());
		}
	}

	public void placeCitInBoxAuto(Citizen subj) {
		
		if (!(placeCitInBoxAutoHelper(subj))) {
			getBox(0).addToBox(subj);
		}
	}
	
	public boolean placeCitInBoxAutoHelper(Citizen subj) {
		if (subj.getHealthStatus()) {
			getBox(1).addToBox(subj);
			return true;
			
		}
		if (subj.getAge() >= 18 && subj.getAge() <= 21) {
			getBox(2).addToBox(subj);
			return true;
			
		}
		return false;
	}
		

	public boolean addCitizen(Citizen subj) {
		if (subj.getName() == null || subj.getName().equals(""))
			return false;
		if (checkCapacity(numOfCitizen, numOfCitizenLogic)) {
			Citizen tempCit = new Citizen(subj);
			electoralPad[numOfCitizen] = tempCit;
			placeCitInBoxAuto(tempCit);
			numOfCitizen++;
			return true;
		} else {
			numOfCitizenLogic *= 2; // working with this ref so didnt create a func
			electoralPad = Arrays.copyOf(electoralPad, numOfCitizenLogic);
			addCitizen(subj);
			return true;
		}

	}

//	public boolean addCitizenGroup(Citizen[] subj) {
//		if (!checkCapacity(numOfCitizen, numOfCitizenLogic))
//			return false;
//		int i = 0;
//		while (i < subj.length) {
//			if (checkCapacity(numOfCitizen, numOfCitizenLogic)) {
//				if (electoralPad[numOfCitizen] == null) {
//					Citizen tempCit = new Citizen(subj[i]);
//					electoralPad[numOfCitizen] = tempCit;
//					placeCitInBox(tempCit);
//					numOfCitizen++;
//					i++;
//				}
//			} else {
//				return false;
//			}
//		}
//		return true;
//	}

	public Citizen addCitizen(String name, int id, int birthYear, BBox ballotBox, boolean underQuarantine,
			boolean protectionGear) {
		if (checkCapacity(numOfCitizen, numOfCitizenLogic)) {

			Citizen tempCit = new Citizen(name, id, birthYear, underQuarantine, protectionGear);
			electoralPad[numOfCitizen] = tempCit;
			placeCitInBoxAuto(tempCit);
			numOfCitizen++;
			return tempCit;
		} else {
			numOfCitizenLogic *= 2;// working with this ref so didnt create a func
			electoralPad = Arrays.copyOf(electoralPad, numOfCitizenLogic);
			return addCitizen(name, id, birthYear, ballotBox, underQuarantine, protectionGear);

		}
	}

	public void showOnesDetails(int id) {
		for (int i = 0; i < electoralPad.length; i++) {
			if (id == electoralPad[i].getId())
				System.out.println(electoralPad[i].toString());

		}
	}

	public int findNominee(int id) { // find nominee,if exist, returns the index where he's stored in electroPad,
										// else return -1
		for (int i = 0; i < electoralPad.length; i++) {
			if (electoralPad[i].getId() == id)
				return i;
		}
		return -1;
	}
}
