package blocks;

public class Citizen {

	private String name;
	private String id;
	private String birthYear;
	private BBox ballotBox;
	private Party vote;
	private boolean underQuarantine;
	private boolean protectionGear;

	public Citizen(Citizen voter) {
		this(voter.getName(), voter.getId(), voter.getBirthYear(), voter.getQStatus(), voter.getProtectionStatus());
		this.ballotBox = voter.getBBox();
	}

	public Citizen(String name, String id, String birthYear, boolean underQuarantine, boolean protectionGear) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.ballotBox = null;
		this.underQuarantine = underQuarantine;
		this.protectionGear = protectionGear;

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBallotBox(BBox ballotBox) {
		this.ballotBox = ballotBox;
	}

	public void setUnderQuarantine(boolean underQuarantine) {
		this.underQuarantine = underQuarantine;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return id;
	}

	public BBox getBBox() {
		return this.ballotBox;
	}

	public String getBirthYear() {
		return this.birthYear;
	}

	public boolean getQStatus() {
		return this.underQuarantine;
	}

	public int getAge() {
		return 2020 - Integer.parseInt(birthYear);
	}

	public boolean getHealthStatus() {
		return underQuarantine;
	}

	public boolean getProtectionStatus() {
		return protectionGear;
	}

	public void vote(Party voteTo) {
		this.vote = voteTo;
	}

	public Party getVote() {
		return this.vote;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Citizen))
			return false;
		Citizen other = (Citizen) obj;
		if (!ballotBox.equals(other.ballotBox))
			return false;
		if (birthYear != other.birthYear)
			return false;
		if (id != other.id)
			return false;
		if (!name.equals(other.name))
			return false;
		if (protectionGear != other.protectionGear)
			return false;
		if (underQuarantine != other.underQuarantine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Name:" + name + " \t Id:" + id + "\t Age:" + getAge() + "\t Infected:" + underQuarantine
				+ "\t Got mask:" + protectionGear;
	}

}
