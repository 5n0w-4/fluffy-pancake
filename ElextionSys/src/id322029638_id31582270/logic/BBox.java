package id322029638_id31582270.logic;

import java.util.Arrays;
import java.util.concurrent.Callable;

import id322029638_id31582270.interfaces.BBoxInterface;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.Voter;
import menu.YesNo;
import set.Set;

public class BBox<T extends Voter> implements BBoxInterface<T> {
	static int id;
	protected int thisId;
	protected String adress;
	protected Set<T> allowedToVoteHere;
	protected Party[] castedVotes;
	protected int numOfCastedVotes; // double?
	protected double percentageOfVotes;
	protected int numOfCitizenWhoCanVote; // double?
	protected int numOfCastedVotesLogic;
	protected Citizen type;
	protected Class<?> typeOfThisBox;

	public BBox(String adress, Class<T> cla22) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.numOfCitizenWhoCanVote = 0;
		this.numOfCastedVotes = 0;
		this.numOfCastedVotesLogic = 1;
		this.allowedToVoteHere = new Set<T>();
		this.castedVotes = new Party[numOfCastedVotesLogic];
		this.typeOfThisBox = cla22;
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
		this.allowedToVoteHere.add(voter);
		this.allowedToVoteHere.getRecent().setVotesAtBallotBox(this);
		numOfCitizenWhoCanVote++;

	}

	public void countVotes() {
		for (T t : allowedToVoteHere) {
			if (t != null) {
				this.addVote(t, t.getMyVote());
			}
		}
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

	public void addVote(T from, Party toParty) {
		if (!(numOfCastedVotes >= numOfCastedVotesLogic)) {

			castedVotes[numOfCastedVotes] = from.getMyVote();
			numOfCastedVotes++;
		} else {
			numOfCastedVotesLogic *= 2;
			castedVotes = Arrays.copyOf(castedVotes, numOfCastedVotesLogic);
			this.addVote(from, toParty);
		}
	}

	public void getData(Callable<?> partyPicker, Callable<?> yesNo) throws Exception {
		for (T t : allowedToVoteHere) {
			if (t != null) {
				YesNo yN = (YesNo) yesNo;
				yN.addName(t.getName());
				t.setVoting((Boolean) yN.call());
				if (t.isVoting()) {
					t.vote((Party) partyPicker.call());
				}
			}

		}
	}

	public void voteAll() { //T extends Citizen
		for (T t : allowedToVoteHere) {
			if (t != null) {
				this.addVote(t, t.getMyVote());
			}
		}

	}

	public String showVoters() {

		StringBuffer buf = new StringBuffer();
		for (Citizen citizen : allowedToVoteHere) {
			try {

				buf.append(citizen.toString() + "\n");
			} catch (NullPointerException e) {
				break;
			}

		}
		return buf.toString();
	}

	private double getVotePrecentage() throws ArithmeticException {
		if (numOfCitizenWhoCanVote > 0)
			return (double) numOfCastedVotes / numOfCitizenWhoCanVote * 100;

		else {
			throw new ArithmeticException();
		}

	}

	public String showRes(Set<Party> partys) {
		StringBuffer buf = new StringBuffer();
		try {
			buf.append("Precentage of votes:" + this.getVotePrecentage()+ "% \n");
			for (Party party : partys) {
				if (party instanceof Party) {
					buf.append("Votes to " + party.getName() + ":" + countVotes(this.castedVotes, party) + "\\"
							+ numOfCitizenWhoCanVote + "\n");
				}
			}
			return buf.toString();
		} catch (ArithmeticException e) {
			buf.append("No voters in this BOX\n");

		}
		return buf.toString();

	}

	public Set<T> getAllowedToVoteHere() {
		return (Set<T>) allowedToVoteHere;
	}

	public String getAdress() {
		return adress;
	}

	public T getById(String id) { // get cit with comparator(like search by value)
		for (T voter : allowedToVoteHere) {
			try {
				if (voter.getId().equals(id))
					return voter;
			} catch (NullPointerException e) {
				break;
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

	public T myType() {
		this.type = new Citizen("starter", "123", "1990", false);
		return (T) this.type;
	}

	public Class getTypeOfThisBox() {
		return typeOfThisBox;
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
		return true;
	}

	@Override
	public String toString() {
		return "Adress:" + adress + "\t  Percentage Of Votes:" + percentageOfVotes;
	}

}
