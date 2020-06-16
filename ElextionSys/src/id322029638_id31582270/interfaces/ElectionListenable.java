package id322029638_id31582270.interfaces;

public interface ElectionListenable {
	void citizenAdded(String name);
	int coronaPatientAsksForDaysInfected();
	boolean voterAsksIfGotMask();
	boolean soliderAsksIfGotWeapon();
	void bBoxAdded(String adress);
	String boxAsksForAdress();
	void partyAdded(String name) ;
	void cantVoteAlert();
}
