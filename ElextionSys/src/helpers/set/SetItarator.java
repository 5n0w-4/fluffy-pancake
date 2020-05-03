package helpers.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SetItarator<T> implements Iterator<T> {
	Set<T> arr;
	int current;

	

	public SetItarator(Set<T> arr) {
		super();
		this.arr = arr;
		this.current = 0;
	}

	@Override
	public boolean hasNext() {
		if (current >= arr.lenght()) {
			return false;
		}
		return true;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		return arr.get(current++);
	}

}
