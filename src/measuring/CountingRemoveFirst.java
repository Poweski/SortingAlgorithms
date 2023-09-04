package measuring;

import java.util.LinkedList;
import java.util.List;

public class CountingRemoveFirst<T> extends Counter {

    public T removeFirstFromList(List<T> list) {
        increment();
        if (list instanceof LinkedList) {
            return ((LinkedList<T>) list).removeFirst();
        }

        return list.remove(0);
    }

    public List<T> removeFirstFromListOfLists(List<List<T>> listOfLists) {
        increment();
        if (listOfLists instanceof LinkedList) {
            return ((LinkedList<List<T>>) listOfLists).removeFirst();
        }

        return listOfLists.remove(0);
    }

}
