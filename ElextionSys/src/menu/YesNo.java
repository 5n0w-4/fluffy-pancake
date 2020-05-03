package menu;

import java.util.concurrent.Callable;

import id322029638_id31582270.ScannerWithMsg;

public class YesNo implements Callable<Boolean> {
	
	String msg;
	String msgWname;
	
	public void load(String msg) {
		this.msg = msg;
	}
	
	public void addName(String name) {
		String temp = name.toLowerCase();
		Character.toUpperCase(temp.charAt(0));

		this.msgWname = temp + " "+ msg;
	}
	

	@Override
	public Boolean call() throws Exception {
		System.out.println(msgWname);
		if (ScannerWithMsg.scanInt("1-Yes \n 2-No") == 1) {
			return true;
		} else
			return false;
	}

}
