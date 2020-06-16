package id322029638_id31582270.logic;

import java.util.ArrayList;

public class ElectionResult {
	private BBox<?> atBox;
	private Party party;
	private int gotVotes;
	private int outOf;
	private double precentage;
	
	public ElectionResult(BBox<?> atBox, Party party, int gotVotes, int outOf, double precentage) {
		super();
		this.atBox = atBox;
		this.party = party;
		this.gotVotes = gotVotes;
		this.outOf = outOf;
		this.precentage = precentage;
	}

	public BBox<?> getAtBox() {
		return atBox;
	}

	public Party getParty() {
		return party;
	}

	public int getGotVotes() {
		return gotVotes;
	}

	public int getOutOf() {
		return outOf;
	}

	public double getPrecentage() {
		return precentage;
	}
	



	
}
