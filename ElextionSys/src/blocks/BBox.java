package blocks;

import java.util.Arrays;

public class BBox {
	static int id;
	protected int thisId;
	protected String adress;
	protected Citizen[] allowedToVoteHere;
	protected Party[] castedVotes;
	protected int numOfCastedVotes; // double?
	protected double percentageOfVotes;
	protected int numOfCitizenWhoCanVote; // double?
	protected int numOfCitizenWhoCanVoteLogic;
	protected int numOfCastedVotesLogic;

	public BBox(String adress) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.numOfCitizenWhoCanVote = 0;
		this.numOfCitizenWhoCanVoteLogic = 1;
		this.numOfCastedVotes = 0;
		this.numOfCastedVotesLogic = 1;
		this.allowedToVoteHere = new Citizen[numOfCitizenWhoCanVoteLogic];
		this.castedVotes = new Party[numOfCastedVotesLogic];
		id++;

	}

	public BBox(BBox copy) {
		this.thisId = copy.thisId;
		this.adress = copy.adress;
		this.allowedToVoteHere = copy.allowedToVoteHere.clone();
		this.castedVotes = copy.castedVotes.clone();
		this.numOfCitizenWhoCanVote = copy.numOfCitizenWhoCanVote;
		this.numOfCitizenWhoCanVoteLogic = copy.numOfCitizenWhoCanVoteLogic;
	}

	public void addToBox(Citizen voter) {
		if (!(numOfCitizenWhoCanVote >= numOfCitizenWhoCanVoteLogic)) {

			this.allowedToVoteHere[numOfCitizenWhoCanVote] = new Citizen(voter);
			this.allowedToVoteHere[numOfCitizenWhoCanVote].setBallotBox(this);
			numOfCitizenWhoCanVote++;

		} else {
			numOfCitizenWhoCanVoteLogic *= 2;
			allowedToVoteHere = Arrays.copyOf(allowedToVoteHere, numOfCitizenWhoCanVoteLogic);
			addToBox(voter);
		}
	}

	private int countVotes(Party[] list, Party countThis) {
		int counter = 0;
		for (Party party : list) {
			if (countThis.equals(party))
				counter++;

		}
		return counter;
	}

	public String vote(Citizen voter) {
		if (!(numOfCastedVotes >= numOfCastedVotesLogic)) {

			castedVotes[numOfCastedVotes] = voter.getVote();
			numOfCastedVotes++;
		} else {
			numOfCastedVotesLogic *= 2;
			castedVotes = Arrays.copyOf(castedVotes, numOfCastedVotesLogic);
		}
		return voter.getName() + " voter for " + voter.getVote().getName();

	}

	public String showVoters() {

		StringBuffer buf = new StringBuffer();
		for (Citizen citizen : allowedToVoteHere) {
			if (citizen instanceof Citizen) {

				buf.append(citizen.toString() + "\n");
			}
		}
		return buf.toString();
	}

	public String showRes(Party[] partys) {
		StringBuffer buf = new StringBuffer();
		if (numOfCitizenWhoCanVote > 0) {// TODO: exeption **div by zero** to be replaced later

			buf.append("Precentage of votes:" + (double) numOfCastedVotes / numOfCitizenWhoCanVote + "\n");
			for (Party party : partys) {
				if (party != null) {
					buf.append("Votes to " + party.getName() + ":" + countVotes(this.castedVotes, party) + "\\"
							+ numOfCitizenWhoCanVote + "\n");
				}
			}
			return buf.toString();
		}
		return "";
	}

	public Citizen[] getAllowedToVoteHere() {
		return allowedToVoteHere;
	}

	public String getAdress() {
		return adress;
	}

	public Citizen getCitizenById(String id) { // get cit with comparator(like search by value)
		for (Citizen citizen : allowedToVoteHere) {
			if (citizen instanceof Citizen) {

				// will implement later
				if (citizen.getId().equals(id)) {
					return citizen;
				}
			}
		}
		return null;
	}
	
	public Party[] getCastedVotes() {
		return castedVotes;
	}

	public int getNumOfCastedVotes() {
		return numOfCastedVotes;
	}

	public double getPercentageOfVotes() {
		return percentageOfVotes;
	}

	public int getNumOfCitizenWhoVote() {
		return numOfCitizenWhoCanVote;
	}

	public int getNumOfCitizenWhoVoteLogic() {
		return numOfCitizenWhoCanVoteLogic;
	}

	public int getNumOfCastedVotesLogic() {
		return numOfCastedVotesLogic;
	}

	public boolean isAdress(String adress) {  //REMOVE?
		if (this.adress.equals(adress)) {
			return true;
		}
		return false;
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
		if (numOfCitizenWhoCanVote != other.numOfCitizenWhoCanVote)
			return false;
		if (numOfCitizenWhoCanVoteLogic != other.numOfCitizenWhoCanVoteLogic)
			return false;
		if (Double.doubleToLongBits(percentageOfVotes) != Double.doubleToLongBits(other.percentageOfVotes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adress:" + adress + "\t  Percentage Of Votes:" + percentageOfVotes;
	}

}
