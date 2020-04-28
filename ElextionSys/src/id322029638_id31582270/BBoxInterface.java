package id322029638_id31582270;

import helpers.Set;

public interface BBoxInterface<T extends Citizen> {
	 void addToBox(T voter);
	T getById(String id);
	String vote(T voter);
	String showVoters();
	String showRes(Set<Party> partys);
	
}
