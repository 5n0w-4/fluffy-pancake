package blocks;


public class Representative extends Citizen {
	private Party underParty;



	public Representative(Citizen voter) {
		super(voter);
		// TODO Auto-generated constructor stub
	}

	public Representative(String name, int id, int birthYear, boolean underQuarantine, boolean protectionGear) {
		super(name, id, birthYear, underQuarantine, protectionGear);
		// TODO Auto-generated constructor stub
	}

	public Party getUnderParty() {
		return underParty;
	}

	public void setUnderParty(Party underParty) {
		this.underParty = underParty;
	}

	
}
