package helpers.set;

public class Test {

	public static void main(String[] args) {

		Set<Integer> s1 = new Set<Integer>();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(5);
		s1.remove(1);
		s1.remove(3);

		System.out.println("index:"+s1.contains(1));

		System.out.println("index:"+s1.contains(5));

		
		System.out.println(s1.toString());
	}

}
