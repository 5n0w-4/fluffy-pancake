package blocks;

import java.util.Arrays;
import java.util.Scanner;

public class BBox {
	static int id;
	protected int thisId;
	protected String adress;
	protected Citizen[] allowedToVoteHere;
	protected Party[] castedVotes;
	protected int numOfCastedVotes;
	protected double percentageOfVotes;
	protected int numOfCitizenWhoVote;
	protected int numOfCitizenWhoVoteLogic;
	protected int numOfCastedVotesLogic;
	protected int citVotedLogic;

	public BBox(String adress) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.numOfCitizenWhoVote = 0;
		this.numOfCitizenWhoVoteLogic = 1;
		this.citVotedLogic = 1;
		this.numOfCastedVotes = 0;
		this.numOfCastedVotesLogic = 1;
		this.allowedToVoteHere = new Citizen[numOfCitizenWhoVoteLogic];
		this.castedVotes = new Party[citVotedLogic];
		id++;
	
	}

	public BBox(BBox copy) {
		this.thisId = copy.thisId;
		this.adress = copy.adress;
		this.allowedToVoteHere = copy.allowedToVoteHere.clone();
		this.castedVotes = copy.castedVotes.clone();
		this.numOfCitizenWhoVote = copy.numOfCitizenWhoVote;
		this.numOfCitizenWhoVoteLogic = copy.numOfCitizenWhoVoteLogic;
		this.citVotedLogic = copy.citVotedLogic;
	}

	public void addToBox(Citizen voter) {
		if (!(numOfCitizenWhoVote >= numOfCitizenWhoVoteLogic)) {
	
			this.allowedToVoteHere[numOfCitizenWhoVote] = new Citizen(voter);
			this.allowedToVoteHere[numOfCitizenWhoVote].setBallotBox(this);
			numOfCitizenWhoVote++;
	
		} else {
			numOfCitizenWhoVoteLogic *= 2;
			allowedToVoteHere = Arrays.copyOf(allowedToVoteHere, numOfCitizenWhoVoteLogic);
			addToBox(voter);
		}
	}

	public Voter convertToVoter(Citizen cit) {

		Voter temp = new Voter(cit);
		return temp;

	}

	private int countVotes(Party[] list, Party countThis) {
		int counter = 0;
		for (Party party : list) {
			if (countThis.equals(party))
				counter++;
	
		}
		return counter;
	}

	public String vote(Voter voter) {
		if (!(numOfCastedVotes >= numOfCastedVotesLogic)) {

			castedVotes[numOfCastedVotes] = voter.getVote();
			numOfCastedVotes++;
		} else {
			numOfCastedVotesLogic *= 2;
			castedVotes = Arrays.copyOf(castedVotes, numOfCastedVotesLogic);
		}
		return voter.getName() + " voter for " + voter.getVote().getClass().getSimpleName();

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
		if (numOfCitizenWhoVote > 0) {// TODO: exeption **div by zero** to be replaced later

			buf.append("Precentage of votes:" + (double) numOfCastedVotes / numOfCitizenWhoVote + "\n");
			for (Party party : partys) {
				if (party != null) {
					buf.append("Votes to " + party.getName() + ":" + countVotes(this.castedVotes, party) + "\\"
							+ numOfCitizenWhoVote + "\n");
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

	public Citizen getCitizen(int id) { //get cit with comparator(like search by value)
		for (Citizen citizen : allowedToVoteHere) { //will implement later
			if (citizen.getId() == id) {
				return citizen;
			}
		}
		return null;
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
