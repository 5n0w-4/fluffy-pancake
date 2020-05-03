package menu;

import java.util.concurrent.Callable;

import helpers.set.Set;
import id322029638_id31582270.Party;
import id322029638_id31582270.ScannerWithMsg;

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
