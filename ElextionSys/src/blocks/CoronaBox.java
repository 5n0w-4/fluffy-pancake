package blocks;

import java.util.Arrays;

public class CoronaBox extends BBox {

	public CoronaBox(String adress) {
		super(adress);
	}

	public CoronaBox(BBox copy) {
		super(copy);
	}

	private boolean canVoteHere(Citizen subj) {
		if (subj.getHealthStatus() && subj.getProtectionStatus()) { // True == Infected
			return true;
		}
		return false;
	}

	@Override
	public String vote(Citizen voter) {
		if (canVoteHere(voter)) {

			if (!(numOfCastedVotes >= numOfCastedVotesLogic)) {

				castedVotes[numOfCastedVotes] = voter.getVote();
				numOfCastedVotes++;
			} else {
				numOfCastedVotesLogic *= 2;
				castedVotes = Arrays.copyOf(castedVotes, numOfCastedVotesLogic);
			}
			return voter.getName() + " voter for " + voter.getVote().getName();
		}
		return "Cant vote here";

	}

}
