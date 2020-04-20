package blocks;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import blocks.Party.WING;

public class Elections {
	private LocalDate date;
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
		this.partys = new Party[numOfPartysLogic];
		this.ballotBoxes = new BBox[numOfBBOxLogic];

	}

	public Elections(Elections copy) {
		this.partys = copy.partys;
		this.ballotBoxes = copy.ballotBoxes;
		this.numOfCitizen = copy.numOfCitizen;
		this.numOfBBOx = copy.numOfBBOx;
		this.numOfPartys = copy.numOfPartys;
		this.numOfCitizenLogic = copy.numOfCitizenLogic;
		this.numOfPartysLogic = copy.numOfPartysLogic;
		this.numOfBBOxLogic = copy.numOfBBOxLogic;
	}

	public void addCitizen(Citizen subj) {
		Citizen tempCit = new Citizen(subj);
		placeCitInBoxAuto(tempCit);
		numOfCitizen++;

	}

	public void addCitizen(String name, int id, int birthYear, boolean underQuarantine,
			boolean protectionGear) {

		Citizen tempCit = new Citizen(name, id, birthYear, underQuarantine, protectionGear);
		placeCitInBoxAuto(tempCit);
		numOfCitizen++;

	}
	public void addCitizen(String name, int id, int birthYear, boolean underQuarantine,
			boolean protectionGear,String adress) { 

		Citizen tempCit = new Citizen(name, id, birthYear, underQuarantine, protectionGear);
		placeCitInBox(tempCit, adress);
		numOfCitizen++;

	}

	public void addCoronaBox(String adress) {
		if (checkCapacity(numOfBBOx, numOfBBOxLogic)) {

			CoronaBox cBox = new CoronaBox(adress);
			ballotBoxes[numOfBBOx] = cBox;
			numOfBBOx++;
		} else {
			numOfBBOxLogic *= 2;// working with this ref so didnt create a func
			ballotBoxes = Arrays.copyOf(ballotBoxes, numOfBBOxLogic);
			addCoronaBox(adress);
		}
	}

	public void addArmyBox(String adress) {
		if (checkCapacity(numOfBBOx, numOfBBOxLogic)) {

			ArmyBox cBox = new ArmyBox(adress);
			ballotBoxes[numOfBBOx] = cBox;
			numOfBBOx++;
		} else {
			numOfBBOxLogic *= 2;// working with this ref so didnt create a func
			ballotBoxes = Arrays.copyOf(ballotBoxes, numOfBBOxLogic);
			addArmyBox(adress);
		}
	}

	public void addBBox(String adress) {
		if (checkCapacity(numOfBBOx, numOfBBOxLogic)) {

			BBox cBox = new BBox(adress);
			ballotBoxes[numOfBBOx] = cBox;
			numOfBBOx++;
		} else {
			numOfBBOxLogic *= 2;// working with this ref so didnt create a func
			ballotBoxes = Arrays.copyOf(ballotBoxes, numOfBBOxLogic);
			addBBox(adress);
		}
	}

	public void placeCitInBox(Citizen citizen, String adress) {
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox && bBox.isAdress(adress)) {
				bBox.addToBox(citizen);
			}
		}
	}
	
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

	public void addNewParty(String name, WING wing) {
		if (checkCapacity(numOfPartys, numOfPartysLogic)) {
			partys[numOfPartys] = new Party(name, wing);
			numOfPartys++;
		} else {
			numOfPartysLogic *= 2;
			partys = Arrays.copyOf(partys, numOfPartysLogic);
			addNewParty(name, wing);
		}
	}
	public void addNewParty(Party party) {
		if (checkCapacity(numOfPartys, numOfPartysLogic)) {
			partys[numOfPartys] = new Party(party);
			numOfPartys++;
		} else {
			numOfPartysLogic *= 2;
			partys = Arrays.copyOf(partys, numOfPartysLogic);
			addNewParty(party);
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
			if (party instanceof Party) {
				buf.append(party.toString()+"\n");
			}
		}
		return buf.toString();
	}

	public String showAllVoters() {
		StringBuffer buf = new StringBuffer();

		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox) {

				buf.append(bBox.showVoters());
			}
		}
		return buf.toString();
	}

	public String showRes() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			if (bBox != null) {
				buf.append("Results in the ballot box on " + bBox.getAdress() + " St. \n");
				buf.append(bBox.showRes(partys) + "\n");
			}
		}
		return buf.toString();
	}

	public void vote(Citizen citizen, BBox bBox, Party toParty) {// add exceptions

		citizen.vote(toParty);
		bBox.vote(citizen);

	}

	public boolean checkCapacity(int size, int sizeLogic) { // Q:Do we have room?
		if (!(size >= sizeLogic)) {
			return true;
		}
		return false;
	}

	public void setRepresentative(Citizen subj, Party party) {
		party.addRep(subj);

	}

	public Party getPartyByName(String Name) {
		for (Party party : partys) {
			if (party instanceof Party) {
				if(party.getName().equals(Name)) {
					return party;
				}
			}
		}
		return null;//no such party
	}
	
	
	public Citizen getCitizenById(int id) {
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox) {
				for (Citizen citizen : bBox.getAllowedToVoteHere()) {
					if (citizen instanceof Citizen) {
						if (citizen.getId() == id) {
							return citizen;
						}
					}
				}
			}
		}
		return null; // no such citizen
	}

	public Party[] getPartys() {
		return partys;
	}

	public int getNumOfPartys() {
		return numOfPartys;
	}

	public BBox getBBox() {

		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox)
				return bBox;
		}
		return null;
	}
	public BBox getBBoxByAdress(String adress) {
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox && bBox.getAdress().equals(adress))
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

	public BBox[] getAllBBox() {
		return this.ballotBoxes;
	}

}
