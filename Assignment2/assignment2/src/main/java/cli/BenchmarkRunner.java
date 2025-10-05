package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * CLI tool for benchmarking MinHeap sort.
 * Runs sorting on different input sizes and saves metrics to CSV.
 */
public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};

        try (FileWriter writer = new FileWriter("benchmark_results.csv")) {
            writer.write("n,time_ms,comparisons,swaps,accesses,recursive_calls\n");

            for (int n : sizes) {
                int[] arr = generateRandomArray(n);

                PerformanceTracker tracker = new PerformanceTracker();
                MinHeap sorter = new MinHeap(tracker);

                long start = System.currentTimeMillis();
                sorter.heapSort(arr);
                long end = System.currentTimeMillis();

                writer.write(n + "," +
                        (end - start) + "," +
                        tracker.getComparisons() + "," +
                        tracker.getSwaps() + "," +
                        tracker.getArrayAccesses() + "," +
                        tracker.getRecursiveCalls() + "\n");

                System.out.println("Done: n=" + n);
            }

            System.out.println("Results saved to benchmark_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random integer array of given size.
     * @param n size of the array
     * @return integer array filled with random numbers
     */
    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }
}
