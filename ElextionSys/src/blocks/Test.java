package blocks;

public class Test {

	public static void main(String[] args) {
		boolean sick = true;
		boolean withProtection = true;
		boolean activeVoter = true;

		int[] date1 = { 10, 2019 };
		int[] date2 = { 07, 2020 };

		int[] date3 = { 10, 2020 };

		Elections spring2020 = new Elections();

//		BBox nB1 = new BBox("123.St");
//		BBox cB1 = new CoronaBox("234.St");
//		BBox aB1 = new ArmyBox("345.St");

//		Party p1 = new Party("1"); // name, creationDate[], representatives[], numOfRepresentatives
//		Party p2 = new Party("2");
//		Party p3 = new Party("3");

//		Citizen c1 = new Citizen("John01", 0001, 1980, nB1, p1, !sick, withProtection, !activeVoter);
//		Citizen c2 = new Citizen("John02", 0010, 1990, cB1, p2, sick, !withProtection, !activeVoter);
//		Citizen c3 = new Citizen("John03", 0100, 2000, aB1, p3, !sick, withProtection, !activeVoter);
//		Citizen c4 = new Citizen("John04", 1000, 2010, cB1, p1, sick, !withProtection, !activeVoter);
//		Citizen c5 = new Citizen("John05", 0101, 1992, nB1, p1, sick, !withProtection, !activeVoter);

//		spring2020.addCitizen(c1);
//		spring2020.addCitizen(c2);
//		spring2020.addCitizen(c3);
//		spring2020.addCitizen(c4);
//		spring2020.addCitizen(c5);

		System.out.println(spring2020.showAllVoters());

	}

}
