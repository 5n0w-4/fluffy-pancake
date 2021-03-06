package id322029638_id31582270.population;

import id322029638_id31582270.interfaces.CitizenInterface;
import id322029638_id31582270.logic.BBox;

public class Citizen implements CitizenInterface {

	private String name;
	private String id;
	private String birthYear;
	private boolean underQuarantine;
//	private BBox<?> ballotType;

	public Citizen(String name, String id, String birthYear, boolean underQuarantine) {
		this.setName(name);
		this.setId(id);
		this.setBirthYear(birthYear);
		this.setUnderQuarantine(underQuarantine);
	}

	public Citizen(Citizen copy) {
		this(copy.getName(), copy.getId(), copy.getBirthYear(), copy.isInfected());

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthYear() {
		return this.birthYear;
	}

	private int getAge() {
		return 2020 - Integer.parseInt(this.birthYear);
	}

	public boolean isInfected() {
		return underQuarantine;
	}

	public void setUnderQuarantine(boolean underQuarantine) {
		this.underQuarantine = underQuarantine;
	}

	public boolean canVote() {
		if (getAge() > 18) {
			return true;
		}
		return false;

	}

	public boolean isInArmy() {
		if (getAge() > 18 && getAge() < 21) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Citizen other = (Citizen) obj;
			if (id == other.getId())
				return true;

		} catch (Exception e) {
			if (e == new NullPointerException()) {
				return false;
			}
		}
		return false;

	}

	@Override
	public String toString() {
		return "Name:" + name + " \t Id:" + id + "\t Age:" + getAge();
	}

}
