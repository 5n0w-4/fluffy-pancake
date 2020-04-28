package helpers;

import java.util.Arrays;
import java.util.Iterator;

import id322029638_id31582270.BBox;
import id322029638_id31582270.Citizen;

public class Set<T> implements SetInterface<T>, Iterable<T> {

	private T[] arr;
	private int index;
	private int ENLARGE_BY;

	public Set() {
		arr = (T[]) new Object[2];
		ENLARGE_BY = 2;
		index = -1;
	}

	@Override
	public void add(T t) {
		if (this.contains(t) == -1) {
			if (arr.length - 1 == this.index) {
				arr = Arrays.copyOf(arr, arr.length * ENLARGE_BY);
			}
			this.index++;
			arr[this.index] = (T)t;

		}
	}

	@Override
	public void addAll(Set<T> value) {
		for (T t : value) {
			this.add(t);
		}
	}

	@Override
	public void addAll(T[] value) {
		for (T t : value) {
			this.add(t);
		}
	}

	@Override
	public void remove(int index) {
		System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
		arr[this.index] = null;
		this.index--;
	}

	@Override
	public void remove(T value) {
		if (this.contains(value) != -1) {
			this.remove(this.contains(value));
		}
	}

	@Override
	public T get(int index) {
		return (T)arr[index];
	}

	@Override
	public void clear() {
		arr = null;
		arr = (T[]) new Object[2];
		index = -1;

	}

	@Override
	public int containsInstance(T val) {
		int index = 0;
		for (T t : arr) {
			if (t != null && t instanceof Citizen) {

				return index;

			}
			index++;
		}
		return -1;

	}
	


	@Override
	public int contains(T val) {
		int index = 0;
		for (T t : arr) {
			if (t != null) {

				if (t.equals(val)) {
					return index;
				}
			}
			index++;
		}
		return -1;

	}

	@Override
	public boolean isEmpty() {
		if (index > -1) {
			return false;
		}
		return true;
	}

	public int lenght() {
		return arr.length;
	}

	@Override
	public T getRecent() {
		return (T)arr[this.index];
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (T t : arr) {
			if (t != null) {

				buf.append(t.toString() + "\n");
			}
		}

		return buf.toString();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SetItarator<T>(this);
	}




}
