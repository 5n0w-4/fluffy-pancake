package id322029638_id31582270.population;

import id322029638_id31582270.exceptions.Invalid_Age;
import id322029638_id31582270.exceptions.Invalid_Id;
import id322029638_id31582270.interfaces.VoterInterface;
import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.Party;

public class Voter extends Citizen implements VoterInterface {
	private BBox<? extends Voter> votesAtBallotBox;
	private Party myVote;
	private boolean isVoting;
	private boolean protectionGear;

	public Voter(Citizen citizen, boolean isVoting, boolean protectionGear) throws Invalid_Age, Invalid_Id {
		super(citizen);
		this.setVoting(isVoting);
		this.setProtectionGear(protectionGear);
	}

	public Voter(Voter copy) throws Invalid_Age, Invalid_Id {
		super(copy);
		this.votesAtBallotBox = copy.getVotesAtBallotBox();
		this.myVote = copy.getMyVote();
		this.isVoting = copy.isVoting();
		this.protectionGear = copy.isProtectionGear();
	}

	@Override
	public BBox<? extends Voter> getVotesAtBallotBox() {
		return votesAtBallotBox;
	}

	@Override
	public void setVotesAtBallotBox(BBox<? extends Voter> votesAtBallotBox) {
		this.votesAtBallotBox = votesAtBallotBox;
	}

	@Override
	public Party getMyVote() {
		return myVote;
	}

	public void setMyVote(Party myVote) {
		this.myVote = myVote;
	}

	public boolean isVoting() {
		return isVoting;
	}

	public void setVoting(boolean isVoting) {
		this.isVoting = isVoting;
	}

	public boolean isProtectionGear() {
		return protectionGear;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}

	@Override
	public boolean canVote() {
		return true;
	}

	@Override
	public boolean isInArmy() {
		return false;
	}

	@Override
	public boolean isInfected() {
		return false;
	}

	@Override
	public void vote(Party voteTo) {
		this.setMyVote(voteTo);
	}

	@Override
	public void setIsVoting(boolean isVoting) {
		this.setIsVoting(isVoting);
	}

}
