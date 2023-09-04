package pivotSelectors;


import pivotSelectors.Exceptions.EmptyListException;

import java.util.List;

public interface PivotSelector<T> {

    T select(List<T> list);

    default void checkIfEmpty(List<T> list) {
        if (list.isEmpty())
            throw new EmptyListException("Empty list!");
    }
}
