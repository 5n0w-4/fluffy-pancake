package id322029638_id31582270.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import id322029638_id31582270.interfaces.BBoxInterface;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.Voter;
import menu.YesNo;

public class BBox<T extends Voter> implements BBoxInterface<T> {
	static int id;
	protected int thisId;
	protected String adress;
	protected ArrayList<T> allowedToVoteHere;
	protected ArrayList<Party> castedVotes;
	protected ArrayList<ElectionResult> electionRes;
	protected int numOfCastedVotes; // double?
	protected double percentageOfVotes;
	protected int numOfCitizenWhoCanVote; // double?
	protected Citizen type;
	protected Class<?> typeOfThisBox;

	public BBox(String adress, Class<T> cla22) {
		this.adress = adress;
		this.thisId = id;
		this.percentageOfVotes = 0;
		this.numOfCitizenWhoCanVote = 0;
		this.numOfCastedVotes = 0;
		this.allowedToVoteHere = new ArrayList<T>();
		this.castedVotes = new ArrayList<Party>();
		this.electionRes = new ArrayList<ElectionResult>();
		this.typeOfThisBox = cla22;
		id++;

	}

	public BBox(BBox<T> copy) {
		this.thisId = copy.thisId;
		this.adress = copy.adress;
		this.allowedToVoteHere.addAll(copy.getAllowedToVoteHere());
		this.castedVotes.addAll(copy.getCastedVotes());
		this.numOfCitizenWhoCanVote = copy.numOfCitizenWhoCanVote;
	}

	@Override
	public void addToBox(T t) {
		this.allowedToVoteHere.add(t);
		this.allowedToVoteHere.get(allowedToVoteHere.size()-1).setVotesAtBallotBox(this);
		numOfCitizenWhoCanVote++;

	}
	public void removeFromBox(T voter) {
		this.allowedToVoteHere.remove(voter);
		numOfCitizenWhoCanVote--;

	}

	public void countVotes() {
		for (T t : allowedToVoteHere) {
			if (t != null) {
				this.castedVotes.add( t.getMyVote());
			}
		}
	}

	public void calcResults(Party countThis) {
		int counter = 0;
		for (Party party : castedVotes) {
			if (party instanceof Party) {
				if (countThis.getName().equals(party.getName()))
					counter++;
			}
		}
		try {
			percentageOfVotes=(double)counter/this.castedVotes.size();
			percentageOfVotes=percentageOfVotes*100;
		} catch (ArithmeticException e) {
			percentageOfVotes=0;
		}
		electionRes.add(new ElectionResult(this,countThis,counter,this.allowedToVoteHere.size(),this.getPercentageOfVotes()));
	}

	public void addVote(String voterID,Party toParty) {
		this.castedVotes.add(toParty);
		this.getById(voterID).vote(toParty);
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
				addVote(t.getId(),t.getMyVote());
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

	public String showRes(ArrayList<Party> partys) {
		StringBuffer buf = new StringBuffer();
		try {
			buf.append("Precentage of votes:" + this.getVotePrecentage()+ "% \n");
			for (Party party : partys) {
				if (party instanceof Party) {
//					ElectionResult workVal = calcResults(party);	///should replace with foreach loop that runs on result array
//					electionRes.add(workVal);
//					buf.append("Votes to " + workVal.getParty().getName() + ":" + workVal.getGotVotes() + "\\"
//							+ workVal.getOutOf() + "\n");
				}
			}
			return buf.toString();
		} catch (ArithmeticException e) {
			buf.append("No voters in this BOX\n");

		}
		return buf.toString();

	}

	public ArrayList<T> getAllowedToVoteHere() {
		return allowedToVoteHere;
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

	public ArrayList<Party> getCastedVotes() {
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

	public ArrayList<ElectionResult> getElectionRes() {
		return electionRes;
	}

	@Override
	public String toString() {
		return "Adress:" + adress + "\t  Percentage Of Votes:" + percentageOfVotes;
	}




}
