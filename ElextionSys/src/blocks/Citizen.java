package blocks;

public class Citizen {

	private String name;
	private int id;
	private int birthYear;
	private BBox ballotBox;
	private Party underParty;
	private boolean underQuarantine;
	private boolean protectionGear;
	public int getId() {
		return id;
	}

	private boolean isVoting;

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setBallotBox(BBox ballotBox) {
		this.ballotBox = ballotBox;
	}

	public void setUnderParty(Party underParty) {
		this.underParty = underParty;
	}

	public void setUnderQuarantine(boolean underQuarantine) {
		this.underQuarantine = underQuarantine;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}

	public Citizen(Citizen voter) {
		this(voter.name, voter.id, voter.birthYear, voter.underParty, voter.underQuarantine, voter.protectionGear,
				voter.isVoting);
		if (voter.ballotBox != null) {

			this.ballotBox = new BBox(voter.ballotBox);
		}
	}
	public Citizen() {
		
	}
	public Citizen(String name, int id, int birthYear, Party underParty, boolean underQuarantine,
			boolean protectionGear, boolean isVoting) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.ballotBox = null;
		this.underParty = underParty;
		this.underQuarantine = underQuarantine;
		this.protectionGear = protectionGear;
		this.isVoting = isVoting;
	}

	public String getName() {
		return this.name;
	}

	public void setIsVoting(boolean status) {
		isVoting = status;
	}

	public boolean getIsVoting() {
		return isVoting;
	}

	public int getAge() {
		return 2020 - birthYear;
	}

	public void setParty(Party party) {
		this.underParty = party;
	}

	public boolean getHealthStatus() {
		return underQuarantine;
	}

	public boolean getProtectionStatus() {
		return protectionGear;
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
		if (!underParty.equals(other.underParty))
			return false;
		if (underQuarantine != other.underQuarantine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [name=" + name + ", id=" + id + ", birthYear=" + birthYear + ", ballotBox=" + ballotBox
				+ ", underParty=" + underParty + ", underQuarantine=" + underQuarantine + ", protectionGear="
				+ protectionGear + "]";
	}

}
