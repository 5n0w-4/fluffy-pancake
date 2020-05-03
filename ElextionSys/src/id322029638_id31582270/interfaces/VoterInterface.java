package id322029638_id31582270.interfaces;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Voter;

public interface VoterInterface {
	void vote(Party voteTo);
	void setIsVoting(boolean isVoting);
	Party getMyVote();
	void setVotesAtBallotBox(BBox<? extends Voter> votesAtBallotBox);
	BBox<? extends Voter> getVotesAtBallotBox();
	boolean isInArmy();
	boolean isInfected();


}
