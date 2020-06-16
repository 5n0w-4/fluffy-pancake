package id322029638_id31582270.interfaces;

import java.util.ArrayList;

import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Citizen;

public interface BBoxInterface<T extends Citizen> {
	 void addToBox(T voter);
	T getById(String id);
	String showVoters();
	String showRes(ArrayList<Party> partys);
	void countVotes();
	
}
