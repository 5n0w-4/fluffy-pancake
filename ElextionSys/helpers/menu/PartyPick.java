package menu;

import java.util.concurrent.Callable;

import id322029638_id31582270.logic.Party;
import scannerWithMsg.ScannerWithMsg;
import set.Set;

public class PartyPick implements Callable<Party> {
	Set<Party> partys;
	public void load(Set<Party> partys) {
		this.partys = partys;
	}
	@Override
	public Party call() throws Exception {
		for (int i = 0; i < partys.lenght(); i++) {
			if (partys.get(i) instanceof Party) {
				Party temp = (Party) partys.get(i);

				System.out.println((i + 1) + " " + temp.getName());
			}
		}
		return (Party) partys.get(ScannerWithMsg.scanInt("") - 1);
	}

}
