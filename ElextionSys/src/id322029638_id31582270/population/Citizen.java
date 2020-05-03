package id322029638_id31582270.population;

import java.awt.Window.Type;

import id322029638_id31582270.BBox;
import id322029638_id31582270.CitizenInerface;
import id322029638_id31582270.Party;

public class Citizen implements CitizenInerface {

	private String name;
	private String id;
	private String birthYear;
	private BBox<Citizen> votesAtBallotBox;
	private Party myVote;
	private boolean isVoting;
	private boolean underQuarantine;
	private boolean protectionGear;

	public Citizen(Citizen voter) {
		this(voter.getName(), voter.getId(), voter.getBirthYear(), voter.getQStatus(),voter.isProtectionGear());
		this.votesAtBallotBox = voter.getBBox();
	}

	public Citizen(String name, String id, String birthYear, boolean underQuarantine, boolean protectionGea) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.votesAtBallotBox = null;
		this.underQuarantine = underQuarantine;
		this.protectionGear = protectionGea;
		this.isVoting = false;

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
@Override
	public void setBallotBox(BBox ballotBox) {
		this.votesAtBallotBox = ballotBox;
	}

	public void setUnderQuarantine(boolean underQuarantine) {
		this.underQuarantine = underQuarantine;
	}


	public String getName() {
		return this.name;
	}

	public String getId() {
		return id;
	}
@Override
	public BBox getBBox() {
		return this.votesAtBallotBox;
	}

	public String getBirthYear() {
		return this.birthYear;
	}

	public boolean getQStatus() {
		return this.underQuarantine;
	}
@Override
	public int getAge() {
		return 2020 - Integer.parseInt(birthYear);
	}

	public boolean getHealthStatus() {
		return underQuarantine;
	}

@Override
	public void vote(Party voteTo) {
		this.myVote = voteTo;
	}
@Override
	public Party getVote() {
		return this.myVote;
	}
	@Override
	public boolean canVote() {
		if (this.getAge()>18) {
			return true;
		}
		return false;
		
		
	}
	
	public boolean isInArmy() {
		if (this.getAge()>18&&this.getAge()<21) {
			return true;
		}
		return false;
	}
@Override
	public boolean isProtectionGear() {
		return protectionGear;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}
	
	

	public boolean isVoting() {
		return isVoting;
	}

	public void setVoting(boolean isVoting) {
		this.isVoting = isVoting;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Citizen))
			return false;
		Citizen other = (Citizen) obj;
		if (!votesAtBallotBox.equals(other.votesAtBallotBox))
			return false;
		if (birthYear != other.birthYear)
			return false;
		if (id != other.id)
			return false;
		if (!name.equals(other.name))
			return false;
		if (underQuarantine != other.underQuarantine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Name:" + name + " \t Id:" + id + "\t Age:" + getAge() + "\t Infected:" + underQuarantine
				+ "\t Got mask:";
	}

}
