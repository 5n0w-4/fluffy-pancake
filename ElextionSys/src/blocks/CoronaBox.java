package blocks;

import java.util.Arrays;

public class CoronaBox extends BBox {

	public CoronaBox(String adress) {
		super(adress);
	}

	public CoronaBox(BBox copy) {
		super(copy);
	}

	private boolean isUnderQ(Citizen subj) {
		if (subj.getHealthStatus()) { // True == Infected
			return true;
		}
		return false;
	}

}
