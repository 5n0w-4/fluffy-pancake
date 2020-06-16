package id322029638_id31582270.interfaces;

import java.util.ArrayList;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.ElectionResult;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.logic.WING;
import id322029638_id31582270.population.Voter;
import set.Set;

public interface ElectionUIListenable {
	void addCitizenIsPressed(String name, String id, String birthYear, boolean underQuarantine);
	<T extends Voter>void addBoxIsPressed(String adress,Class<?> type);
	void addPartyIsPressed(String name, WING wing) ;
	void setRepIsPressed(String id, String partyName) ;
	void startVotingIsPressed(boolean isVoting,Party toParty,BBox<?> box,String voterName);
	void fireCountVotesEvent();
	ArrayList<Party> viewAsksGetPartys();
	<T extends Voter> ArrayList<T> viewAsksGetCitizen();
	ArrayList<BBox<?>> viewAsksGetBoxes();
	void changeWindowSize(int hight,int width);
	
}
