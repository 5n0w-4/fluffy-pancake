package set;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.population.Citizen;
import id322029638_id31582270.population.Solider;

public interface SetInterface<T> {
	void add(T val);
	void addAll(T[] val);
	void addAll(Set<T> vals);
	void remove(int index);
	void remove(T value);
	T get(int index);
	T getRecent();
	void clear();
	int lenght();
	int containsInstance(T val);
	int contains(T val);//return index
	boolean isEmpty();
	String toString();
}
