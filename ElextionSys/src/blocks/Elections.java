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

	private int numOfBBOxLogic;
	private int numOfPartysLogic;

	public Elections() {
		this.date = LocalDate.now();
		this.numOfCitizen = 0;
		this.numOfBBOx = 0;
		this.numOfPartys = 0;
		this.numOfCitizenLogic = 1;
		this.numOfBBOxLogic = 1;
		this.numOfPartysLogic = 1;
		this.electoralPad = new Citizen[numOfCitizenLogic];
		this.partys = new Party[numOfPartysLogic];
		this.ballotBoxes = new BBox[numOfBBOxLogic];

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

//	public BBox getBox(Citizen citizen) { // coronaBox 1 armyBox 2 bBox 0
//		for (BBox bBox : ballotBoxes) {
//			if (bBox instanceof CoronaBox && citizen.getQStatus()) {
//				return bBox;
//			}
//			if (bBox instanceof ArmyBox && citizen.getAge() > 18 && citizen.getAge() < 21) {
//				return bBox;
//			}
//			if (bBox instanceof BBox) {
//				return bBox;
//			}
//		}
//		return null;
//	}

	public void placeCitInBoxAuto(Citizen citizen) { // would like to phrase as try ---> (add to regular box) catch(1)
														// {cit infected} ---> add to coronaBox catch(2) {cit is
														// solider} ---> add to ArmyBox, finall ---> ?self test?
		if (!(citizen.getQStatus() || (citizen.getAge() > 18 && citizen.getAge() < 21))) { // negative logic, would like
																							// to phrase as exceptions.
			getBBox().addToBox(citizen);

		}

		if (citizen.getQStatus()) {
			getCoronaBox().addToBox(citizen);
		}
		if (citizen.getAge() > 18 && citizen.getAge() < 21) {
			getArmyBox().addToBox(citizen);
		}
	}

	public Voter makeVoter(Citizen citizen) { // maybe to remove
		return new Voter(citizen);
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

				buf.append(bBox.getAdress()+ "\n"+bBox.showVoters() + "\n");
			}
		}
		return buf.toString();
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

	public void setRepresentative(Citizen subj, String partyName) {
		Party temp = search(partyName);
		temp.addRep(subj);

	}

	public Party[] getPartys() {
		return partys;
	}

	public int getNumOfPartys() {
		return numOfPartys;
	}

	public Citizen[] getElectoralPad() {
		return electoralPad;
	}

	public BBox getBBox() {

		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox)
				return bBox;
		}
		return null;
	}

	public CoronaBox getCoronaBox() {

		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof CoronaBox)
				return (CoronaBox) bBox;
		}
		return null;
	}

	public ArmyBox getArmyBox() {

		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof ArmyBox)
				return (ArmyBox) bBox;
		}
		return null;
	}
}
