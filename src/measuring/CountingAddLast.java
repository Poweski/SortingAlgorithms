package measuring;

import java.util.LinkedList;
import java.util.List;

public class CountingAddLast<T> extends Counter {

    public void addLast(List<T> list, T element) {
        increment();
        if (list instanceof LinkedList<T>) {
            ((LinkedList<T>) list).addLast(element);
            return;
        }

        list.add(element);
    }

    public void addLast(List<List<T>> listOfLists, List<T> listElement) {
        increment();
        if (listOfLists instanceof LinkedList<List<T>>) {
            LinkedList<List<T>> list = (LinkedList<List<T>>) listOfLists;
            list.addLast(listElement);
            return;
        }

        listOfLists.add(listElement);
    }

}
