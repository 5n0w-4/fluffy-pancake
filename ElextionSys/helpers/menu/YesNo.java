package menu;

import java.util.concurrent.Callable;

import scannerWithMsg.ScannerWithMsg;

public class YesNo implements Callable<Boolean> {

	String msg;

	public void load(String msg) {
		this.msg = msg;
	}

	public void addName(String name) {
		String temp = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();

		this.msg = temp + " " + msg;
	}

	@Override
	public Boolean call() throws Exception {
		System.out.println(msg);
		if (ScannerWithMsg.scanInt("1-Yes \n 2-No") == 1) {
			return true;
		} else
			return false;
	}

}
