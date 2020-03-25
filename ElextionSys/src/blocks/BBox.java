package blocks;

import java.util.Arrays;

public class BBox {
	static int id;
	protected int thisId;
	protected String adress;
	protected Citizen[] allowedToVoteHere;
	protected Party[] castedVotes;
	protected double percentageOfVotes;
	private int citVoted;
	private int numOfCitizenWhoVote;
	private int numOfCitizenWhoVoteLogic;
	private int citVotedLogic;

	public BBox(String adress) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.citVoted = 0;
		this.numOfCitizenWhoVote = 0;
		this.numOfCitizenWhoVoteLogic = 1;
		this.citVotedLogic = 1;
		this.allowedToVoteHere = new Citizen[numOfCitizenWhoVoteLogic];
		this.castedVotes = new Party[citVotedLogic];
		id++;

	}

	public BBox(String adress, Citizen[] allowedToVoteHere) {
		this(adress);
		this.allowedToVoteHere = allowedToVoteHere;

	}

	public BBox(BBox copy) {
		this.thisId = copy.thisId;
		this.adress = copy.adress;
		this.allowedToVoteHere = copy.allowedToVoteHere.clone();
		this.castedVotes = copy.castedVotes.clone();
		this.citVoted = copy.citVoted;
		this.numOfCitizenWhoVote = copy.numOfCitizenWhoVote;
		this.numOfCitizenWhoVoteLogic = copy.numOfCitizenWhoVoteLogic;
		this.citVotedLogic = copy.citVotedLogic;
	}

	public String vote(Citizen voter, Party toParty) {
		for (int i = 0; i < numOfCitizenWhoVote; i++) {
			if (castedVotes[i] == null) {
				castedVotes[i] = toParty;
				citVoted++;
			}
		}
		return voter.getName() + " voter for " + toParty.getClass().getSimpleName();

	}

	public void addToBox(Citizen voter) {
		if (!(numOfCitizenWhoVote >= numOfCitizenWhoVoteLogic)) {

			this.allowedToVoteHere[numOfCitizenWhoVote] = new Citizen(voter);
			numOfCitizenWhoVote++;
		} else {
			numOfCitizenWhoVoteLogic *= 2;
			allowedToVoteHere = Arrays.copyOf(allowedToVoteHere, numOfCitizenWhoVoteLogic);
			addToBox(voter);
		}
	}

	public int countVotes(Party[] list, Party countThis) {
		int counter = 0;
		for (Party party : list) {
			if (countThis.equals(party))
				counter++;

		}
		return counter;
	}

	public String showVoters() {

		StringBuffer buf = new StringBuffer();
		for (Citizen citizen : allowedToVoteHere) {
			if (citizen != null) {

				buf.append(citizen.toString()+"\n");
			}
		}
		return buf.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BBox))
			return false;
		BBox other = (BBox) obj;

		if (!adress.equals(other.adress))
			return false;
		if (this.thisId != other.thisId)
			return false;
		if (!Arrays.equals(allowedToVoteHere, other.allowedToVoteHere))
			return false;
		if (numOfCitizenWhoVote != other.numOfCitizenWhoVote)
			return false;
		if (numOfCitizenWhoVoteLogic != other.numOfCitizenWhoVoteLogic)
			return false;
		if (Double.doubleToLongBits(percentageOfVotes) != Double.doubleToLongBits(other.percentageOfVotes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BBox [adress=" + adress + ", allowedToVoteHere=" + Arrays.toString(allowedToVoteHere)
				+ ", percentageOfVotes=" + percentageOfVotes + ", numOfCitizenWhoVote=" + numOfCitizenWhoVote
				+ ", numOfCitizenWhoVoteLogic=" + numOfCitizenWhoVoteLogic + "]";
	}

}