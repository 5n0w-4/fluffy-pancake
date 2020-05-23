package id322029638_id31582270.logic;

import java.time.LocalDate;
import java.util.concurrent.Callable;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.Solider;
import id322029638_id31582270.population.Voter;
import set.Set;

public class Elections {
	private LocalDate date;
	private Set<Party> partys;
	private Set<BBox<?>> boxes;

	public Elections() {
		this.date = LocalDate.now();
		this.partys = new Set<Party>();
		this.boxes = new Set<BBox<?>>();

	}

	public Elections(Elections copy) {
		this.partys = copy.partys;

	}

	public <T extends Voter> void addCitizen(Citizen subj) {
		T tempCit = allocate(subj);
		placeCitInBoxAuto(tempCit);

	}

	public <T extends Voter> void addCitizen(String name, String id, String birthYear, boolean underQuarantine) {
		Citizen tempCit = new Citizen(name, id, birthYear, underQuarantine);
		T temp = allocate(tempCit);
		if (temp != null) {
			placeCitInBoxAuto(temp);
		}

	}
	


	private <T extends Voter> T allocate(Citizen citizen) { // vals for testing
		if (citizen.canVote()) {
			Voter voter = new Voter(citizen, false, false);

			if (citizen.isInArmy()) {
				Solider solider = new Solider(voter, false);
				if (citizen.isInfected()) {
					InfectedSolider infectedSolider = new InfectedSolider(voter, 0, false);
					return (T) infectedSolider;
				}
				return (T) solider;
			}
			if (citizen.isInfected()) {
				CoronoaPatient coronaPatient = new CoronoaPatient(voter, 0);
				return (T) coronaPatient;
			}
			return (T) voter;
		}
		return null;

	}

	public void addArmyCoronaBox(String adress) {
		boxes.add(new BBox<InfectedSolider>((adress), InfectedSolider.class));
	}

	public void addCoronaBox(String adress) {
		boxes.add(new BBox<CoronoaPatient>((adress), CoronoaPatient.class));

	}

	public void addArmyBox(String adress) {
		boxes.add(new BBox<Solider>((adress), Solider.class));
	}

	public void addBBox(String adress) {
		boxes.add(new BBox<Voter>((adress), Voter.class));
	}

	public <T extends Voter> void placeInBox(T subj) {

		this.getBox(subj).addToBox(subj);

	}

	public <T extends Voter> void addBox(String adress, T voter) {

		boxes.add(new BBox<T>((adress), (Class<T>) voter.getClass()));

	}

	public <T extends Voter> void placeCitInBoxAuto(T voter) { // exceptions: boxMissing ---> ADDbox

		BBox<T> temp = this.getBox(voter);
		if (temp == null) {
			this.addBox(scannerWithMsg.ScannerWithMsg.scanStr("Enter adress:"), voter);
			temp = this.getBox(voter);
		}
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
		for (BBox bBox : boxes) {
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

		for (BBox bBox : boxes) {
			if (bBox instanceof BBox) {

				buf.append(bBox.showVoters());
			}
		}
		return buf.toString();
	}

	public String showRes() {
		StringBuffer buf = new StringBuffer();
		for (BBox bBox : boxes) {
			if (bBox instanceof BBox) {
				buf.append("Results in the ballot box on " + bBox.getAdress() + " St. \n");
				buf.append(bBox.showRes(partys) + "\n");
			}
		}
		return buf.toString();
	}

	public <T extends Citizen> void countVotes() {// add exceptions
		for (BBox<?> box : boxes) {
			if (box != null) {
				box.countVotes();
			}
		}

	}

	public void setRepresentative(Voter subj, Party party) {
		party.addRep(subj);

	}

	public <T extends Voter> T getCitizenById(String id) {
		for (BBox bBox : boxes) {
			if (bBox instanceof BBox) {
				if (bBox.getById(id) instanceof Citizen) {
					return (T) bBox.getById(id);
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

	public <T extends Citizen> void getData(Callable<Party> partyPicker, Callable<Boolean> yesNo) throws Exception {
		for (BBox<?> bBox : boxes) {
			if (bBox != null) {
				bBox.getData(partyPicker, yesNo);

			}
		}
	}

	public <T extends Citizen> void vote() {
		for (BBox<?> box : boxes) {

			box.voteAll();

		}
	}

	public <T extends Voter> BBox<T> getBox(T type) {
		for (BBox<?> bBox : boxes) {
			if (bBox != null) {
				if (bBox.getTypeOfThisBox() == type.getClass()) {
					return (BBox<T>) bBox;
				}
			}

		}
		return null;
	}

	public Set<BBox<?>> getAllBoxes() {
		return boxes;
	}

}
