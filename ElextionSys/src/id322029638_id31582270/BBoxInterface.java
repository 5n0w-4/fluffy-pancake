package id322029638_id31582270;

import helpers.set.Set;
import id322029638_id31582270.population.Citizen;

public interface BBoxInterface<T extends Citizen> {
	 void addToBox(T voter);
	T getById(String id);
	String showVoters();
	String showRes(Set<Party> partys);
	void countVotes();
	
}
