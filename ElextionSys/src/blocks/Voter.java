package blocks;

public class Voter extends Citizen {

	public Voter(String name, int id, int birthYear, boolean underQuarantine, boolean protectionGear) {
		super(name, id, birthYear, underQuarantine, protectionGear);
		// TODO Auto-generated constructor stub
	}
	private Party vote;
	public Voter(Citizen cit) {
		super(cit.getName(),cit.getId(),cit.getBirthYear(),cit.getQStatus(),cit.getProtectionStatus());
		this.setBallotBox(cit.getBBox());
	}
	
	public void Vote(Party voteTo) {
		this.vote = voteTo;
		
	}
	public Party getVote() {
		return this.vote;
	}

}
