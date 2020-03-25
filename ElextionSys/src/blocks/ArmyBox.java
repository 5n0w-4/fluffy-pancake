package blocks;

public class ArmyBox extends BBox {

	public ArmyBox(String adress) {
		super(adress);
	}

	public ArmyBox(BBox copy) {
		super(copy);
	}

	public ArmyBox(String adress, Citizen[] allowedToVoteHere) {
		super(adress, allowedToVoteHere);

	}

	public boolean isInArmy(Citizen subj) {
		if (subj.getAge() >= 18 && subj.getAge() <= 21) {
			return true;
		}
		return false;
	}
}
