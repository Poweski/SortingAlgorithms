package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import measuring.*;

public abstract class SortingAlgorithm<T> {
	
	private final CountingComparator<T> comparator;
	private final CountingSwapper<T> swapper;
	private final CountingAddFirst<T> firstAdder;
	private final CountingAddLast<T> lastAdder;
	private final CountingRemoveFirst<T> firstRemover;
	private final CountingRemoveLast<T> lastRemover;

	public SortingAlgorithm(Comparator<? super T> comparator) {
		this.comparator = new CountingComparator<T>(comparator);
		swapper = new CountingSwapper<T>();
		firstAdder = new CountingAddFirst<>();
		lastAdder = new CountingAddLast<>();
		firstRemover = new CountingRemoveFirst<>();
		lastRemover = new CountingRemoveLast<>();
	}

	public void reset() {
		comparator.reset();
		swapper.reset();
		firstAdder.reset();
		lastAdder.reset();
		firstRemover.reset();
	}
	
	public long comparisons() {
		return comparator.count();
	}
	public long swaps() {
		return swapper.count();
	}
	public long addFirstNumber() { return firstAdder.count();}
	public long addLastNumber() { return lastAdder.count();}
	public long removeFirstNumber() { return firstRemover.count();}
	public long removeLastNumber() { return lastRemover.count();}

	public Comparator<? super T> baseComparator() {
		return comparator.baseComparator();
	}
	
	protected int compare(T lhs, T rhs) {
		return comparator.compare(lhs, rhs);
	}
	
	protected void swap(List<T> list, int index1, int index2) {
		swapper.swap(list, index1, index2);
	}

	public abstract List<T> sort(List<T> list);


	protected void addFirst(List<T> list, T element) {
		firstAdder.addFirst(list, element);
	}

	protected void addFirst(List<List<T>> listOfLists, List<T> elementList) {
		firstAdder.addFirst(listOfLists, elementList);
	}

	protected void addLast(List<T> list, T element) {
		lastAdder.addLast(list, element);
	}

	protected void addLast(List<List<T>> listOfLists, List<T> elementList) {
		lastAdder.addLast(listOfLists, elementList);
	}

	protected T removeFirstFromList(List<T> list) {
		return firstRemover.removeFirstFromList(list);
	}

	protected List<T> removeFirstFromListOfLists(List<List<T>> listOfLists) {
		return lastRemover.removeLastFromListOfLists(listOfLists);
	}

	protected T removeLastFromList(List<T> list) {
		return lastRemover.removeLastFromList(list);
	}

	protected List<T> removeLastFromListOfLists(List<List<T>> listOfLists) {
		return lastRemover.removeLastFromListOfLists(listOfLists);
	}

	protected List<T> getNewListInTheSameType(List<T> list) {
		if (list instanceof LinkedList<T>)
			return new LinkedList<>();
		return new ArrayList<>();
	}
}