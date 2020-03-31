package blocks;

import java.time.LocalDate;
import java.util.Arrays;

public class Party {
	private String name;
	private LocalDate creationDate;
	private String wingDirect;
	enum wing {
		right, left, center
	};

	private Citizen[] representatives;
	private int numOfRepresentatives;
	private int numOfRepresentativesLogic;
	public void setWing(String choose)
	{
		
	}
	public Party(String name,String wing) {
		this.name = name;
		this.wingDirect=wing;
		this.creationDate = LocalDate.now();
		this.numOfRepresentatives = 0;
		this.numOfRepresentativesLogic = 1;
		this.representatives = new Citizen[numOfRepresentativesLogic];

	}
	

	public String getWingDirect() {
		return wingDirect;
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
		representatives[numOfRepresentatives] = subj;
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
				+ ", numOfRepresentativesLogic=" + numOfRepresentativesLogic +" ,wingDirect="+wingDirect+ "]";
	}

}
