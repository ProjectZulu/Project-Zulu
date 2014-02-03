package projectzulu.common.world2;

import org.apache.commons.lang3.tuple.Pair;

/**
 * For usage see: {@link Pair}
 */
public class BoundaryPair<K, V> {
    private final K lower;
    private final V upper;

    public static <K, V> BoundaryPair<K, V> createPair(K lower, V upper) {
        return new BoundaryPair<K, V>(lower, upper);
    }

    public BoundaryPair(K lower, V upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public K getLowerLimit() {
        return lower;
    }

    public V getUpperLimit() {
        return upper;
    }
}