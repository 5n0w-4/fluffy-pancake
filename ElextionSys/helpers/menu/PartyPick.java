package menu;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import id322029638_id31582270.logic.Party;
import scannerWithMsg.ScannerWithMsg;
import set.Set;

public class PartyPick implements Callable<Party> {
	ArrayList<Party> partys;
	public void load(ArrayList<Party> arrayList) {
		this.partys = arrayList;
	}
	@Override
	public Party call() throws Exception {
		for (int i = 0; i < partys.size(); i++) {
			if (partys.get(i) instanceof Party) {
				Party temp = (Party) partys.get(i);

				System.out.println((i + 1) + " " + temp.getName());
			}
		}
		return (Party) partys.get(ScannerWithMsg.scanInt("") - 1);
	}

}
