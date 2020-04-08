package blocks;

public class Citizen {

	private String name;
	private int id;
	private int birthYear;
	private BBox ballotBox;
	private boolean underQuarantine;
	private boolean protectionGear;

	public int getId() {
		return id;
	}

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

	public void setUnderQuarantine(boolean underQuarantine) {
		this.underQuarantine = underQuarantine;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}

	public BBox getBBox() {
		return this.ballotBox;
	}

	public int getBirthYear() {
		return this.birthYear;
	}

	public boolean getQStatus() {
		return this.underQuarantine;
	}

	public Citizen(Citizen voter) {
		this(voter.getName(), voter.getId(), voter.getBirthYear(), voter.getQStatus(), voter.getProtectionStatus());
		this.ballotBox = voter.getBBox();
	}

	public Citizen(String name, int id, int birthYear, boolean underQuarantine, boolean protectionGear) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.ballotBox = null;
		this.underQuarantine = underQuarantine;
		this.protectionGear = protectionGear;

	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return 2020 - birthYear;
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
		if (underQuarantine != other.underQuarantine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [name=" + name + ", id=" + id + ", birthYear=" + birthYear 
				+ ", underQuarantine=" + underQuarantine + ", protectionGear=" + protectionGear + "]";
	}

}
