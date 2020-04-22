package blocks;

import java.util.Arrays;

public class ArmyBox extends BBox {

	public ArmyBox(String adress) {
		super(adress);
	}

	public ArmyBox(BBox copy) {
		super(copy);
	}

	public boolean isInArmy(Citizen subj) {
		if (subj.getAge() >= 18 && subj.getAge() <= 21) {
			return true;
		}
		return false;
	}
	
	@Override
	public String vote(Citizen voter) {
		if (isInArmy(voter)) {

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
