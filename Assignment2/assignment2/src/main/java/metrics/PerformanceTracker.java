package metrics;

/**
 * Utility class for tracking performance metrics of algorithms.
 * Stores number of comparisons, swaps, array accesses, and recursive calls.
 */
public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long recursiveCalls = 0;

    /** Increments comparison counter */
    public void incrementComparisons() {
        comparisons++;
    }

    /** Increments swap counter */
    public void incrementSwaps() {
        swaps++;
    }

    /**
     * Increments array access counter by given amount.
     * @param count number of array accesses to add
     */
    public void incrementAccesses(int count) {
        arrayAccesses += count;
    }

    /** Increments recursive calls counter */
    public void incrementRecursiveCalls() {
        recursiveCalls++;
    }

    /** @return total number of comparisons */
    public long getComparisons() {
        return comparisons;
    }

    /** @return total number of swaps */
    public long getSwaps() {
        return swaps;
    }

    /** @return total number of array accesses */
    public long getArrayAccesses() {
        return arrayAccesses;
    }

    /** @return total number of recursive calls */
    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    /** Resets all counters to zero */
    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        recursiveCalls = 0;
    }
}
