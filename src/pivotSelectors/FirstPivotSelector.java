package pivotSelectors;

import java.util.LinkedList;
import java.util.List;

public class FirstPivotSelector<T> implements PivotSelector<T> {
    @Override
    public T select(List<T> list) {
        checkIfEmpty(list);
        if (list instanceof LinkedList)
            return ((LinkedList<T>) list).removeFirst();
        return list.remove(0);
    }
}