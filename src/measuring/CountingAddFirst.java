package measuring;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CountingAddFirst<T> extends Counter {


    public void addFirst(List<T> list, T element) {
        increment();
        if (list instanceof LinkedList<T>) {
            ((LinkedList<T>) list).addFirst(element);
            return;
        }
        list.add(0, element);
    }

    public void addFirst(List<List<T>> listOfLists, List<T> listElement) {
        increment();
        if (listOfLists instanceof LinkedList<List<T>>) {
            LinkedList<List<T>> list = (LinkedList<List<T>>) listOfLists;
            list.addFirst(listElement);
            return;
        }

        listOfLists.add(0, listElement);
    }

}

