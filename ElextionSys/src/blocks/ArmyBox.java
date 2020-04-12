package blocks;

import java.util.Arrays;

public class ArmyBox extends BBox {

	public ArmyBox(String adress) {
		super(adress);
	}

	public ArmyBox(BBox copy) {
		super(copy);
	}

	public boolean isInArmy(Citizen subj) {
		if (subj.getAge() >= 18 && subj.getAge() <= 21) {
			return true;
		}
		return false;
	}

}
