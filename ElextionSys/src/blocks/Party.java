package blocks;

import java.time.LocalDate;
import java.util.Arrays;

public class Party  {
	private String name;
	private LocalDate creationDate;

	enum WING {
		right, left, center
	};

	WING wingDirect;
	private Representative[] representatives;
	private int numOfRepresentatives;
	private int numOfRepresentativesLogic;

	public void setWing(String wingDirect) {
		this.wingDirect = WING.valueOf(wingDirect);

	}
	
	public int compareName(String other) {
		if (this.name.compareTo(other)==0) {
			return 1;
		}
		return 0;
	}
	
	

	public void addRep(Citizen someOne) {
		if (numOfRepresentatives < numOfRepresentativesLogic) {
			representatives[numOfRepresentatives] = new Representative(someOne);
			numOfRepresentatives++;
		}
		else {
			numOfRepresentativesLogic *= 2;
			representatives = Arrays.copyOf(representatives, numOfRepresentativesLogic);
			addRep(someOne);
		}
	}

	public String getName() {
		return name;
	}

	public Party(String name, String wing) {
		this.name = name;
		this.wingDirect =  WING.valueOf(wing);
		this.creationDate = LocalDate.now();
		this.numOfRepresentatives = 0;
		this.numOfRepresentativesLogic = 1;
		this.representatives = new Representative[numOfRepresentativesLogic];

	}

	public String getWingDirect() {
		return wingDirect.toString();
	}

	public void setWingDirect(String wingDirect) {
		this.wingDirect = wingDirect;
	}

	public Party(Party copy) {
		this.name = copy.name;
		this.creationDate = copy.creationDate;
		this.numOfRepresentatives = copy.numOfRepresentatives;
		this.numOfRepresentativesLogic = copy.numOfRepresentativesLogic;
		this.representatives = copy.representatives.clone();
	}

	public void addRepresentative(Citizen subj) {
		representatives[numOfRepresentatives] = (Representative)subj;
		numOfRepresentatives++;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Party))
			return false;
		Party other = (Party) obj;

		if (!creationDate.isEqual(other.creationDate))
			return false;
		if (!name.equals(other.name))
			return false;
		if (numOfRepresentatives != other.numOfRepresentatives)
			return false;
		if (numOfRepresentativesLogic != other.numOfRepresentativesLogic)
			return false;
		if (!Arrays.equals(representatives, other.representatives))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Party [name=" + name + ", creationDate=" + creationDate.toString() + ", representatives="
				+ Arrays.toString(representatives) + ", numOfRepresentatives=" + numOfRepresentatives
				+ ", numOfRepresentativesLogic=" + numOfRepresentativesLogic + " ,wingDirect=" + wingDirect + "]";
	}

}
