package id322029638_id31582270.logic;

import java.util.ArrayList;

import id322029638_id31582270.GUI.ElectionView;
import id322029638_id31582270.interfaces.ElectionListenable;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.interfaces.SoliderMarker;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.CoronoaPatient;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.Solider;
import id322029638_id31582270.population.Voter;

public class ElectionController implements ElectionListenable, ElectionUIListenable {
	Elections theModel;
	ElectionView theView;

	public ElectionController(Elections theModel, ElectionView theView) {
		this.theModel = theModel;
		this.theView = theView;

		theModel.registerListner(this);
		theView.registerListner(this);
		// hard coding of few citizens, parties, and bbox
		// parties
		Party[] partys = new Party[4];
		partys[0] = new Party("Halicud", WING.right);
		partys[1] = new Party("Kahol-lavan", WING.center);
		partys[2] = new Party("IL our home", WING.right);
		partys[3] = new Party("Haavoda", WING.left);

		// citizens
		Citizen[] testCit = new Citizen[12];
		testCit[0] = new Citizen("shlomy", "1", "1966", true);
		testCit[1] = new Citizen("yossi", "2", "2001", true);
		testCit[2] = new Citizen("hagit", "3", "2001", false);
		testCit[3] = new Citizen("orly", "4", "1980", true);
		testCit[4] = new Citizen("amir", "5", "2001", false);
		testCit[5] = new Citizen("moti", "6", "2001", true);

		testCit[6] = new Citizen("Bibi", "7", "1949", false);
		testCit[7] = new Citizen("Gidon Saar", "8", "1966", false);
		testCit[8] = new Citizen("Beni Gantz", "9", "1959", false);
		testCit[9] = new Citizen("Gabi Ashkenazi", "10", "1954", false);
		testCit[10] = new Citizen("Avigdor Liberman", "11", "1958", false);
		testCit[11] = new Citizen("Amir Peretz", "12", "1952", false);

		// Ballot boxes
		theModel.addBBox("mivtza kadesh 38");
		theModel.addBBox("eben gabirol 35");
		theModel.addBBox("hkovshim 28");
		theModel.addCoronaBox("derech shiba 2");
		theModel.addArmyBox("classified");

		for (Party party : partys) {
			theModel.addNewParty(party);
		}
		for (Citizen citizen : testCit) {
			theModel.addCitizen(citizen);
		}

		theModel.setRepresentative(new Voter(theModel.getCitizenById("2"), false, true), partys[0]);
		theModel.setRepresentative(new Voter(theModel.getCitizenById("3"), true, true), partys[3]);
		theModel.setRepresentative(new Voter(theModel.getCitizenById("4"), true, true), partys[3]);
		theModel.setRepresentative(new Voter(theModel.getCitizenById("7"), true, true), partys[3]);

	}

	@Override
	public void addCitizenIsPressed(String name, String id, String birthYear, boolean underQuarantine) {
		theModel.addCitizen(name, id, birthYear, underQuarantine);
	}

	@Override
	public<T extends Voter> void addBoxIsPressed(String adress,Class<?> type) {
		theModel.addBox(adress, (Class<T>) type);

	}

	@Override
	public void addPartyIsPressed(String name, WING wing) {
		theModel.addNewParty(name, wing);
	}

	@Override
	public void setRepIsPressed(String id, String partyName) {
		theModel.setRepresentative(theModel.getCitizenById(id), theModel.getPartyByName(partyName));
	}

	@Override
	public void startVotingIsPressed(boolean isVoting, Party toParty, BBox<?> box, String voterID) {
		theModel.vote(box, toParty, voterID);
		box.getById(voterID).setIsVoting(isVoting);
	}
	

	@Override
	public ArrayList<Party> viewAsksGetPartys() {
		return theModel.getPartys();
	}

	@Override
	public <T extends Voter> ArrayList<T> viewAsksGetCitizen() {
		return theModel.getAllVoters();
	}

	@Override
	public ArrayList<BBox<?>> viewAsksGetBoxes() {
		return theModel.getAllBoxes();
	}

	@Override
	public void fireCountVotesEvent() {
		theModel.countVotes();
	}

	@Override
	public void changeWindowSize(int hight, int width) {
		theView.changeWindowSize(hight, width);
	}


	//////////////////////////////////////////////////
	/////////////////////////////////////////////////
	////////////////////////////////////////////////

	@Override
	public void citizenAdded(String name) {
		theView.showAddedAlert("Citizen " +name);
	}

	@Override
	public void bBoxAdded(String adress) {
		theView.showAddedAlert("Ballot Box on " +adress + " Street");
	}

	@Override
	public void partyAdded(String name) {
		theView.showAddedAlert("Party " +name);
	}

	@Override
	public void cantVoteAlert() {
		theView.showErrorAlert("Citizen got no mask");
		
	}

	@Override
	public int coronaPatientAsksForDaysInfected() {
		return Integer.parseInt(theView.askAdditionalInfoString("Since you are infected, \nI would like to know for how many days."));
		
	}

	@Override
	public boolean voterAsksIfGotMask() {
		return theView.askAdditionalInfoBoolean("mask?");
	}
	
	public boolean soliderAsksIfGotWeapon() {
		return theView.askAdditionalInfoBoolean("weapon?");
	}

	@Override
	public String boxAsksForAdress() {
		// TODO Auto-generated method stub
		return theView.askAdditionalInfoString("The box for this voter type is missing. \nPlease enter adress for a NEW box.");
	}

	
	


}
