package blocks;

import java.time.LocalDate;
import java.util.Arrays;

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
		startPartys();
		startBoxes();
		startPad();
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
			Citizen temp = addCitizen("John " + i, i * 11111111, 1990 + i, null, null, false, false, false);
		}
	}

	public void startPartys() {
		for (int i = 0; i < 3; i++) {
			addNewParty("Party " + i);
		}
	}

	public void startBoxes() {
		addNewBBox(1, "123.St");
		addNewBBox(2, "234.St");
		addNewBBox(0, "345.St");
	}

	public boolean checkCapacity(int size, int sizeLogic) { // Q:Do we have room?
		if (!(size >= sizeLogic)) {
			return true;
		}
		return false;
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

	public void addNewParty(String name) {
		if (checkCapacity(numOfPartys, numOfPartysLogic)) {
			partys[numOfPartys] = new Party(name);
			numOfPartys++;
		} else {
			numOfPartysLogic *= 2;
			partys = Arrays.copyOf(partys, numOfPartysLogic);
			addNewParty(name);
		}
	}

	public String showAllBBox() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			buf.append(bBox.toString());
		}
		return buf.toString();
	}

	public String showAllPartys() {
		StringBuffer buf = new StringBuffer();
		for (Party party : partys) {
			buf.append(party.toString());
		}
		return buf.toString();
	}

	public String showAllVoters() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : ballotBoxes) {
			if (bBox instanceof BBox) {

				buf.append(bBox.showVoters()+"\n");
			}
		}
		return buf.toString();
	}

	public void setRepresentative(Citizen subj, Party party) {
		party.addRepresentative(subj);
		subj.setParty(party);
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

	public void placeCitInBox(Citizen subj) {
		if (subj.getHealthStatus()) {
			getBox(1).addToBox(subj);
		}
		if (subj.getAge() >= 18 && subj.getAge() <= 21) {
			getBox(2).addToBox(subj);
		} else {
			getBox(0).addToBox(subj);
		}
	}

	public void addCitizen(Citizen subj) {
		if (checkCapacity(numOfCitizen, numOfCitizenLogic)) {
			Citizen tempCit = new Citizen(subj);
			electoralPad[numOfCitizen] = tempCit;
			placeCitInBox(tempCit);
			numOfCitizen++;
		} else {
			numOfCitizenLogic *= 2; // working with this ref so didnt create a func
			electoralPad = Arrays.copyOf(electoralPad, numOfCitizenLogic);
			addCitizen(subj);
		}
	}

	public Citizen addCitizen(String name, int id, int birthYear, BBox ballotBox, Party underParty,
			boolean underQuarantine, boolean protectionGear, boolean activeVoter) {
		if (checkCapacity(numOfCitizen, numOfCitizenLogic)) {

			Citizen tempCit = new Citizen(name, id, birthYear, underParty, underQuarantine, protectionGear,
					activeVoter);
			electoralPad[numOfCitizen] = tempCit;
			placeCitInBox(tempCit);
			numOfCitizen++;
			return tempCit;
		} else {
			numOfCitizenLogic *= 2;// working with this ref so didnt create a func
			electoralPad = Arrays.copyOf(electoralPad, numOfCitizenLogic);
			return addCitizen(name, id, birthYear, ballotBox, underParty, underQuarantine, protectionGear, activeVoter);
			
		}
	}
}
