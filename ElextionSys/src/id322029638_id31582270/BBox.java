package id322029638_id31582270;

import java.util.Arrays;

import helpers.Set;

public class BBox<T extends Citizen> implements BBoxInterface<T> {
	static int id;
	protected int thisId;
	protected String adress;
	protected Set<T> allowedToVoteHere;
	protected Party[] castedVotes;
	protected int numOfCastedVotes; // double?
	protected double percentageOfVotes;
	protected int numOfCitizenWhoCanVote; // double?
	protected int numOfCastedVotesLogic;

	public BBox(String adress) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.numOfCitizenWhoCanVote = 0;
		this.numOfCastedVotes = 0;
		this.numOfCastedVotesLogic = 1;
		this.allowedToVoteHere = new Set<T>();
		this.castedVotes = new Party[numOfCastedVotesLogic];
		id++;

	}

	public BBox(BBox<T> copy) {
		this.thisId = copy.thisId;
		this.adress = copy.adress;
		this.allowedToVoteHere.addAll(copy.getAllowedToVoteHere());
		this.castedVotes = copy.castedVotes.clone();
		this.numOfCitizenWhoCanVote = copy.numOfCitizenWhoCanVote;
	}

	@Override
	public void addToBox(T voter) {
			this.allowedToVoteHere.add((T)new Citizen(voter));
			this.allowedToVoteHere.getRecent().setBallotBox(this);
			numOfCitizenWhoCanVote++;
		
	}

	private int countVotes(Party[] list, Party countThis) {
		int counter = 0;
		for (Party party : list) {
			if (party instanceof Party) {

				if (countThis.getName().equals(party.getName()))
					counter++;
			}
		}
		return counter;
	}

	public String vote(T voter) {
		if (!(numOfCastedVotes >= numOfCastedVotesLogic)) {

			castedVotes[numOfCastedVotes] = voter.getVote();
			numOfCastedVotes++;
		} else {
			numOfCastedVotesLogic *= 2;
			castedVotes = Arrays.copyOf(castedVotes, numOfCastedVotesLogic);
			this.vote(voter);
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

	public String showRes(Set<Party> partys) {
		StringBuffer buf = new StringBuffer();
		if (numOfCitizenWhoCanVote > 0) {// TODO: exeption **div by zero** to be replaced later

			buf.append("Precentage of votes:" + (double) numOfCastedVotes / numOfCitizenWhoCanVote + "% \n");
			for (Party party : partys) {
				if (party instanceof Party) {
					buf.append("Votes to " + party.getName() + ":" + countVotes(this.castedVotes, party) + "\\"
							+ numOfCitizenWhoCanVote + "\n");
				}
			}
			return buf.toString();
		}
		return "";
	}

	public Set<T> getAllowedToVoteHere() {
		return allowedToVoteHere;
	}

	public String getAdress() {
		return adress;
	}

	public T getById(String id) { // get cit with comparator(like search by value)
		for (T voter : allowedToVoteHere) {
			if (voter !=null) {

				// will implement later
				if (voter.getId().equals(id)) {
					return voter;
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



	public int getNumOfCastedVotesLogic() {
		return numOfCastedVotesLogic;
	}

	public boolean isAdress(String adress) { // REMOVE?
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
//		if (!Arrays.equals(allowedToVoteHere, other.allowedToVoteHere))
//			return false;
		if (numOfCitizenWhoCanVote != other.numOfCitizenWhoCanVote)
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
