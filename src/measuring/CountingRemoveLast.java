package measuring;

import java.util.LinkedList;
import java.util.List;

public class CountingRemoveLast<T> extends Counter {

    public T removeLastFromList(List<T> list) {
        increment();
        if (list instanceof LinkedList) {
            return ((LinkedList<T>) list).removeLast();
        }

        return list.remove(list.size()-1);
    }

    public List<T> removeLastFromListOfLists(List<List<T>> listOfLists) {
        increment();
        if (listOfLists instanceof LinkedList) {
            return ((LinkedList<List<T>>) listOfLists).removeLast();
        }

        return listOfLists.remove(listOfLists.size()-1);
    }

}
