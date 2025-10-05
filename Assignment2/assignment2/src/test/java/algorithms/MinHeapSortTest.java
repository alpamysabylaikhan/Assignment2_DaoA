package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapSortTest {

    @Test
    public void testEmptyArray() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {};
        sorter.heapSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElement() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {42};
        sorter.heapSort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    public void testDuplicates() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {5, 1, 5, 1, 5};
        sorter.heapSort(arr);
        assertTrue(isSortedDescending(arr));
    }

    @Test
    public void testAlreadySorted() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {1, 2, 3, 4, 5};
        sorter.heapSort(arr);
        assertTrue(isSortedDescending(arr));
    }

    @Test
    public void testReverseSorted() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {5, 4, 3, 2, 1};
        sorter.heapSort(arr);
        assertTrue(isSortedDescending(arr));
    }

    // ---------- 2) Property-based testing ----------
    @Test
    public void testRandomArrays() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        Random rand = new Random();
        for (int t = 0; t < 20; t++) { // 20 случайных массивов
            int[] arr = rand.ints(50, 0, 100).toArray();
            int[] copy = Arrays.copyOf(arr, arr.length);

            sorter.heapSort(arr);
            Arrays.sort(copy);
            reverse(copy); // так как MinHeapSort сортирует по убыванию

            assertArrayEquals(copy, arr);
        }
    }

    // ---------- 3) Cross-validation ----------
    @Test
    public void testCrossValidation() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = {7, 2, 9, 1, 3};
        int[] copy = Arrays.copyOf(arr, arr.length);

        sorter.heapSort(arr);
        Arrays.sort(copy);
        reverse(copy);

        assertArrayEquals(copy, arr);
    }

    // ---------- 4) Performance tests ----------
    @Test
    public void testScalability() {
        int[] sizes = {100, 1000, 10000};
        for (int n : sizes) {
            PerformanceTracker tracker = new PerformanceTracker();
            MinHeap sorter = new MinHeap(tracker);

            int[] arr = new Random().ints(n, 0, 10000).toArray();

            long start = System.currentTimeMillis();
            sorter.heapSort(arr);
            long end = System.currentTimeMillis();

            System.out.println("n=" + n +
                    " time=" + (end - start) + "ms " +
                    "comparisons=" + tracker.getComparisons() +
                    " swaps=" + tracker.getSwaps());

            assertTrue(isSortedDescending(arr));
        }
    }

    // ---------- 5) Input distribution tests ----------
    @Test
    public void testInputDistributions() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] sorted = {1,2,3,4,5,6,7,8,9};
        int[] reversed = {9,8,7,6,5,4,3,2,1};
        int[] nearlySorted = {1,2,3,5,4,6,7,8,9};
        int[] random = {4,9,1,7,3,6,8,2,5};

        sorter.heapSort(sorted);
        sorter.heapSort(reversed);
        sorter.heapSort(nearlySorted);
        sorter.heapSort(random);

        assertTrue(isSortedDescending(sorted));
        assertTrue(isSortedDescending(reversed));
        assertTrue(isSortedDescending(nearlySorted));
        assertTrue(isSortedDescending(random));
    }

    // ---------- 6) Memory profiling ----------
    @Test
    public void testMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long before = runtime.totalMemory() - runtime.freeMemory();

        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap sorter = new MinHeap(tracker);

        int[] arr = new Random().ints(50000, 0, 1000000).toArray();
        sorter.heapSort(arr);

        long after = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Memory used: " + (after - before) + " bytes");
        assertTrue(isSortedDescending(arr));
    }

    // ---------- Helpers ----------
    private boolean isSortedDescending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) return false;
        }
        return true;
    }

    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
    }
}
