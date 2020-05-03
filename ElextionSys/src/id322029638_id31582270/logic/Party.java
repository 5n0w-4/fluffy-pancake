package id322029638_id31582270.logic;

import java.time.LocalDate;
import java.util.Arrays;

import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.Representative;
import id322029638_id31582270.population.Voter;

public class Party {
	WING wingDirect;
	private String name;
	private LocalDate creationDate;
	private Representative[] representatives;
	private int numOfRepresentatives;
	private int numOfRepresentativesLogic;

	public Party(String name, WING wing) {
		this.name = name;
		this.wingDirect = wing;
		this.creationDate = LocalDate.now();
		this.numOfRepresentatives = 0;
		this.numOfRepresentativesLogic = 1;
		this.representatives = new Representative[numOfRepresentativesLogic];
	
	}

	public Party(Party copy) {
		this.name = copy.name;
		this.creationDate = copy.creationDate;
		this.numOfRepresentatives = copy.numOfRepresentatives;
		this.numOfRepresentativesLogic = copy.numOfRepresentativesLogic;
		this.wingDirect = copy.wingDirect;
		this.representatives = copy.representatives.clone();
	}

	public void setWing(String wingDirect) {
		this.wingDirect = WING.valueOf(wingDirect);

	}

	public void addRep(Voter someOne) {
		if (numOfRepresentatives < numOfRepresentativesLogic) {
			representatives[numOfRepresentatives] = new Representative(someOne,this);
			numOfRepresentatives++;
		} else {
			numOfRepresentativesLogic *= 2;
			representatives = Arrays.copyOf(representatives, numOfRepresentativesLogic);
			addRep(someOne);
		}
	}

	public int compareName(String other) {
		if (this.name.compareTo(other) == 0) {
			return 1;
		}
		return 0;
	}

	public void setWingDirect(String wingDirect) {
		this.wingDirect = WING.valueOf(wingDirect);
	}

	public String getName() {
		return name;
	}

	public String getWingDirect() {
		return wingDirect.toString();
	}

	public String[] getRepNames() {
		String[] temp = new String[representatives.length];
		int i = 0;
		for (Citizen citizen : representatives) {
			if (citizen instanceof Citizen) {

				temp[i] = citizen.getName();
				i++;
			}
		}
		return temp;
	}
	
	

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public Representative[] getRepresentatives() {
		return representatives;
	}

	public int getNumOfRepresentatives() {
		return numOfRepresentatives;
	}

	public int getNumOfRepresentativesLogic() {
		return numOfRepresentativesLogic;
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
		return "Party name:" + name + "\t Founded on:" + creationDate.toString() + "\t Represented by:"
				+ Arrays.toString(getRepNames()) + "\t  Political direction:" + wingDirect.toString();
	}

}

