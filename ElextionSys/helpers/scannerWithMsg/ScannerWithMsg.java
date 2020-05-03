package scannerWithMsg;

import java.util.Scanner;

public class ScannerWithMsg {
	static Scanner scan = new Scanner(System.in);
	
	public static String scanStr(String msg) {
		System.out.println(msg);
		return scan.next();
	}
	
	public static int scanInt(String msg) {
		System.out.println(msg);
		return scan.nextInt();
	}
	
	public static boolean scanBool(String msg) {
		System.out.println(msg);
		return scan.nextBoolean();
	}

}
