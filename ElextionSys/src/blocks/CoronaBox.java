package blocks;

public class CoronaBox extends BBox {

	public CoronaBox(String adress) {
		super(adress);
	}

	public CoronaBox(BBox copy) {
		super(copy);
	}

	public CoronaBox(String adress, Citizen[] allowedToVoteHere) {
		super(adress, allowedToVoteHere);
	}

	private boolean isUnderQ(Citizen subj) {
		if (subj.getHealthStatus()) { // True = Infected
			return true;
		}
		return false;
	}
}
