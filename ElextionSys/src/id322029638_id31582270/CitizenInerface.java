package id322029638_id31582270;

public interface CitizenInerface {

	void vote(Party voteTo);
	void setVoting(boolean isVoting);
	int getAge();
	boolean canVote();
	boolean isProtectionGear();
	Party getVote();
	void setBallotBox(BBox ballotBox);
	BBox getBBox();
}
