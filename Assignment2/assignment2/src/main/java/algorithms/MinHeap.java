package algorithms;

import metrics.PerformanceTracker;

/**
 * Implementation of MinHeap-based Heap Sort.
 * Sorts the array in descending order using a min-heap structure.
 * Tracks performance metrics (comparisons, swaps, array accesses, recursive calls).
 */
public class MinHeap {
    private final PerformanceTracker tracker;

    /**
     * Constructor for MinHeap.
     * @param tracker PerformanceTracker instance to record metrics
     */
    public MinHeap(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Sorts the input array using min-heap sort algorithm.
     * @param arr input array to be sorted (cannot be null)
     * @throws IllegalArgumentException if input array is null
     */
    public void heapSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int n = arr.length;

        // Build min heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    /**
     * Maintains the min-heap property for a subtree rooted at index i.
     * @param arr array to heapify
     * @param n size of the heap
     * @param i root index of the subtree
     */
    private void heapify(int[] arr, int n, int i) {
        tracker.incrementRecursiveCalls();

        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        tracker.incrementComparisons();
        if (left < n) {
            tracker.incrementAccesses(2);
            if (arr[left] < arr[smallest]) {
                smallest = left;
            }
        }

        tracker.incrementComparisons();
        if (right < n) {
            tracker.incrementAccesses(2);
            if (arr[right] < arr[smallest]) {
                smallest = right;
            }
        }

        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, n, smallest);
        }
    }

    /**
     * Swaps two elements in the array and updates metrics.
     * @param arr array containing elements
     * @param i index of first element
     * @param j index of second element
     */
    private void swap(int[] arr, int i, int j) {
        tracker.incrementSwaps();
        tracker.incrementAccesses(4); // 2 reads + 2 writes
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
