package id322029638_id31582270;

import java.time.LocalDate;

import javax.swing.plaf.SliderUI;

import helpers.Set;

public class Elections {
	private LocalDate date;
	private Set<Party> partys;
	private Set<BBox<Citizen>> normBoxes; // 0
	private Set<BBox<CoronoaPatient>> coronaBoxes; // 1
	private Set<BBox<Solider>> armyBoxes; // 2

	public Elections() {
		this.date = LocalDate.now();
		this.partys = new Set<Party>();
		this.normBoxes = new Set<BBox<Citizen>>();
		this.coronaBoxes = new Set<BBox<CoronoaPatient>>();
		this.armyBoxes = new Set<BBox<Solider>>();

	}

	public Elections(Elections copy) {
		this.partys = copy.partys;
		this.normBoxes = copy.normBoxes;

	}

	public <T extends Citizen> void addCitizen(Citizen subj) {
		T tempCit = (T) new Citizen(subj);
		placeCitInBoxAuto(tempCit);

	}

	public <T extends Citizen> void addCitizen(String name, String id, String birthYear, boolean underQuarantine, boolean protectionGear) {

		T tempCit = (T) new Citizen(name, id, birthYear, underQuarantine, protectionGear);
		placeCitInBoxAuto(tempCit);

	}

//	public void addCitizen(String name, String id, String birthYear, boolean underQuarantine, boolean protectionGear,
//			String adress) { // adress to add to specific box
//
//		Citizen tempCit = new Citizen(name, id, birthYear, underQuarantine,protectionGear);
//		placeCitInBox(tempCit, adress);
//
//	}

	public <T extends Citizen> void addCoronaBox(String adress) {
		coronaBoxes.add(new BBox<CoronoaPatient>(adress));

	}

	public <T extends Citizen> void addArmyBox(String adress) {

		armyBoxes.add(new BBox<Solider>(adress));

	}

	public <T extends Citizen> void addBBox(String adress) {
		normBoxes.add(new BBox<Citizen>(adress));

	}
//
//	public<T extends Citizen> void placeCitInBox(T voter, String adress) {
//		for (BBox<T> bBox : normBoxes) {
//			if (bBox instanceof BBox && bBox.isAdress(adress)) {
//				bBox.addToBox(voter);
//			}
//		}
//	}

	public<T extends Citizen> void placeCitInBoxAuto(T voter) { // would like to phrase as try ---> (add to regular
		// box) catch(1)
// {cit infected} ---> add to coronaBox catch(2) {cit is
// solider} ---> add to ArmyBox, finall ---> ?self test?
//if (!(voter.getQStatus() || (voter.getAge() > 18 && voter.getAge() < 21))) { // negative logic, would like
//								// to phrase as exceptions.
//getBBox().addToBox(voter);
//
//}
//
//if (voter.getQStatus() && !(voter.getAge() > 18 && voter.getAge() < 21)) {
//getCoronaBox().addToBox(voter);
//}
//if (voter.getAge() > 18 && voter.getAge() < 21) {
//getArmyBox().addToBox(voter);
//}
//
//
		BBox<T> temp = this.getBBox(voter);
		temp.addToBox(voter);
		
	}

	public void addNewParty(String name, WING wing) {
		partys.add(new Party(name, wing));

	}

	public void addNewParty(Party party) {
		partys.add(new Party(party));

	}

	public String showAllBBox() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : normBoxes) {
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
				buf.append(party.toString() + "\n");
			}
		}
		return buf.toString();
	}

	public String showAllVoters() {
		StringBuffer buf = new StringBuffer();

		for (BBox bBox : normBoxes) {
			if (bBox instanceof BBox) {

				buf.append(bBox.showVoters());
			}
		}
		return buf.toString();
	}

	public String showRes() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : normBoxes) {
			if (bBox instanceof BBox) {
				buf.append("Results in the ballot box on " + bBox.getAdress() + " St. \n");
				buf.append(bBox.showRes(partys) + "\n");
			}
		}
		return buf.toString();
	}

	public String vote(Citizen citizen, BBox bBox, Party toParty) {// add exceptions
		citizen.vote(toParty);
		return bBox.vote(citizen);

	}

	public void setRepresentative(Citizen subj, Party party) {
		party.addRep(subj);

	}

	public Citizen getCitizenById(String id) {
		for (BBox bBox : normBoxes) {
			if (bBox instanceof BBox) {
				if (bBox.getById(id) instanceof Citizen) {
					return bBox.getById(id);
				}

			}
		}
		return null; // no such citizen
	}

	public LocalDate getDate() {
		return this.date;
	}

	public Set<Party> getPartys() {
		return partys;
	}

	public Party getPartyByName(String Name) {
		for (Party party : partys) {
			if (party instanceof Party) {
				if (party.getName().equals(Name)) {
					return party;
				}
			}
		}
		return null;// no such party
	}

//	public BBox getBBoxByAdress(String adress) {
//		for (BBox bBox : normBoxes) {
//			if (bBox instanceof BBox && bBox.getAdress().equals(adress))
//				return bBox;
//		}
//		return null;
//	}

	public <T extends Citizen> BBox<T> getBBox(T voter) {
		if (voter instanceof CoronoaPatient) {
			return getCoronaBox();
		}
		if (voter instanceof Solider) {
			return getArmyBox();
		}
		if (voter instanceof Citizen) {
			return getBox();
		}
		return null;
	}

	public <T extends Citizen> BBox<T> getBox() {

		return (BBox<T>)normBoxes.getRecent();
	}

	public <T extends Citizen> BBox<T> getCoronaBox() {

		return (BBox<T>)coronaBoxes.getRecent();
	}

	public  <T extends Citizen> BBox<T> getArmyBox() {

		return (BBox<T>)armyBoxes.getRecent();
	}

//	public  Set<BBox<?>> getAllCoronaBox() {
//		return coronaBoxes;
//	}
//	
//	public  Set<BBox<?>> getAllArmyBox() {
//		return armyBoxes;
//	}
//	
//	public  Set<BBox<?>> getAllBBox() {
//		return normBoxes;
//	}

}
