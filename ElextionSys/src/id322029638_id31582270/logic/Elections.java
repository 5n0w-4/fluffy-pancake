package id322029638_id31582270.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import id322029638_id31582270.interfaces.ElectionListenable;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.InfectedRepresentative;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.InfectedSoliderRepresentative;
import id322029638_id31582270.population.Representative;
import id322029638_id31582270.population.Solider;
import id322029638_id31582270.population.SoliderRepresentative;
import id322029638_id31582270.population.Voter;
import javafx.util.Pair;
import set.Set;

public class Elections {
	private LocalDate date;
	private ArrayList<Party> partys;
	private ArrayList<BBox<?>> boxes;
	private ArrayList<ElectionListenable> listners = new ArrayList<>();

	public Elections() {
		this.date = LocalDate.now();
		this.partys = new ArrayList<Party>();
		this.boxes = new ArrayList<BBox<?>>();

	}

	public Elections(Elections copy) {
		this.partys = copy.partys;

	}

	public void registerListner(ElectionListenable listner) {
		listners.add(listner);
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
				Solider solider = new Solider(voter, listners.get(0).soliderAsksIfGotWeapon());
				if (citizen.isInfected()) {
					InfectedSolider infectedSolider = new InfectedSolider(voter);
					infectedSolider.setDaysInfected(listners.get(0).coronaPatientAsksForDaysInfected());
					infectedSolider.setCarryWeapon(listners.get(0).soliderAsksIfGotWeapon());
					infectedSolider.setProtectionGear(listners.get(0).voterAsksIfGotMask());
					return (T) infectedSolider;
				}
				return (T) solider;
			}
			if (citizen.isInfected()) {

				CoronoaPatient coronaPatient = new CoronoaPatient(voter,
						listners.get(0).coronaPatientAsksForDaysInfected());
				coronaPatient.setProtectionGear(listners.get(0).voterAsksIfGotMask());
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

	public <T extends Voter> void addBox(String adress, T voter) {
		boxes.add(new BBox<T>((adress), (Class<T>) voter.getClass()));
	}

	public <T extends Voter> void addBox(String adress, Class<T> voter) {
		boxes.add(new BBox<T>((adress), voter));
		for (ElectionListenable electionUIListenable : listners) {
			electionUIListenable.bBoxAdded(adress);
		}

	}

	public <T extends Voter> void placeCitInBoxAuto(T voter) { // exceptions: boxMissing ---> ADDbox

		BBox<T> temp = this.getBox(voter);
		if (temp == null) {
			this.addBox(listners.get(0).boxAsksForAdress(), voter);
			temp = this.getBox(voter);
		}
		temp.addToBox(voter);
		for (ElectionListenable electionUIListenable : listners) {
			electionUIListenable.citizenAdded(voter.getName());
		}

	}

	public void addNewParty(String name, WING wing) {
		partys.add(new Party(name, wing));
		for (ElectionListenable electionUIListenable : listners) {
			electionUIListenable.partyAdded(name);
		}

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
				for (Party party : partys) {
					box.calcResults(party);

				}
			}
		}

	}

	public <T extends Voter> void setRepresentative(T subj, Party party) {
		party.addRep(subj);
		T rep = (T) new Representative(subj, party);

		if (subj instanceof SoliderMarker) {
			rep = (T) new SoliderRepresentative((Solider) subj, party);
		}
		if (subj instanceof SickMarker) {
			rep = (T) new InfectedRepresentative((CoronoaPatient) subj, party);
		}
		if (subj instanceof SickMarker && subj instanceof SoliderMarker) {
			rep = (T) new InfectedSoliderRepresentative((InfectedSolider) subj, party);
		}

		Pair<Integer, Integer> temp = getPath(subj.getId());
		BBox<T> tempBox = (BBox<T>) boxes.get(temp.getKey());
		tempBox.addToBox(rep);
		tempBox.removeFromBox(subj);

	}

	private Pair<Integer, Integer> getPath(String id) {
		int i = 0;
		for (BBox bBox : boxes) {
			int k = 0;
			if (bBox instanceof BBox) {
				if (bBox.getById(id) instanceof Citizen) {
					return new Pair<Integer, Integer>(i, k);
				}
				k++;
			}
			i++;
		}
		return null; // no such citizen
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

	public ArrayList<Party> getPartys() {
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

	public <T extends Voter> void vote(BBox<?> box, Party toParty, String voterID) {
		if (getCitizenById(voterID).isInfected() && !getCitizenById(voterID).isProtectionGear()) {
			for (ElectionListenable electionUIListenable : listners) {
				electionUIListenable.cantVoteAlert();
			}
		} else {
			box.addVote(voterID, toParty);
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

	public ArrayList<BBox<?>> getAllBoxes() {
		return boxes;
	}

	public <T extends Voter> ArrayList<T> getAllVoters() {
		ArrayList<T> temp = new ArrayList<T>();
		for (BBox<?> bBox : boxes) {
			if (bBox != null) {
				temp.addAll((Collection<? extends T>) bBox.getAllowedToVoteHere());
			}
		}
		return temp;
	}

}
