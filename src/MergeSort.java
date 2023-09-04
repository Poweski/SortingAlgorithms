import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSort<T> extends SortingAlgorithm<T> {

    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort (List<T> list) {

        LinkedList<List<T>> queue = new LinkedList<>();

        while (list.size() > 0) {
            List<T> innerQueue = getNewListInTheSameType(list);
            addFirst(innerQueue, removeLastFromList(list));
            addFirst(queue, innerQueue);
        }

        if (queue.size() == 0)
            return getNewListInTheSameType(list);

        List<T> firstQueue;
        List<T> secondQueue;

        while (queue.size() > 1) {
            firstQueue = removeLastFromListOfLists(queue);
            secondQueue = removeLastFromListOfLists(queue);
            queue.addFirst(merge(firstQueue, secondQueue));
        }

        return removeLastFromListOfLists(queue);
    }

    private List<T> merge (List<T> list1, List<T> list2) {

        List<T> newList = getNewListInTheSameType(list1);
        T element1 = null;
        T element2 = null;

        while ((!list1.isEmpty() || element1 != null) && (!list2.isEmpty() || element2 != null)) {

            if (element1 == null)
                element1 = removeLastFromList(list1);

            if (element2 == null)
                element2 = removeLastFromList(list2);

            if (compare(element1, element2) > 0) {
                addFirst(newList, element1);
                element1 = null;
            }
            else {
                addFirst(newList, element2);
                element2 = null;
            }
        }

        if (element1 != null || list1.size() > 0) {

            if (element1 != null)
                addFirst(newList, element1);

            while (list1.size() > 0)
                addFirst(newList, removeLastFromList(list1));
        }
        else if (element2 != null || list2.size() > 0) {

            if (element2 != null)
                addFirst(newList, element2);

            while (list2.size() > 0)
                addFirst(newList, removeLastFromList(list2));
        }

        return newList;
    }
}