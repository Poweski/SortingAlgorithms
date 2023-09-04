package pivotSelectors;

import java.util.List;
import java.util.Random;

public class RandomPivotSelector<T> implements PivotSelector<T>{
    @Override
    public T select(List<T> list) {
        checkIfEmpty(list);
        int index = (new Random()).nextInt(list.size());
        return list.remove(index);
    }
}
