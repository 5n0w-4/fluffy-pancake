package id322029638_id31582270.interfaces;

import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Citizen;
import set.Set;

public interface BBoxInterface<T extends Citizen> {
	 void addToBox(T voter);
	T getById(String id);
	String showVoters();
	String showRes(Set<Party> partys);
	void countVotes();
	
}
