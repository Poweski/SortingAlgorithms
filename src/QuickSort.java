import core.SortingAlgorithm;
import pivotSelectors.PivotSelector;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> extends SortingAlgorithm<T> {

    int recursiveID = 0;
    private final PivotSelector<T> pivotSelector;
    public QuickSort(Comparator<? super T> comparator, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.pivotSelector = pivotSelector;
    }

    @Override
    public List<T> sort(List<T> list) {

        recursiveID = 0;

        if (list.size() <= 1)
            return list;

        List<T> smallerOrEqual = getNewListInTheSameType(list);
        List<T> bigger = getNewListInTheSameType(list);
        T pivot = pivotSelector.select(list);
        T element;

        while (list.size() > 0) {

            if (compare(element = removeFirstFromList(list), pivot) <= 0)
                addLast(smallerOrEqual, element);

            else
                addLast(bigger, element);
        }

        return concatenate(sort(smallerOrEqual), pivot, sort(bigger));
    }

    private List<T> concatenate(List<T> smallerOrEqual, T pivot, List<T> bigger) {

        addLast(smallerOrEqual, pivot);

        while (bigger.size() > 0)
            addLast(smallerOrEqual, removeFirstFromList(bigger));

        return smallerOrEqual;
    }
}