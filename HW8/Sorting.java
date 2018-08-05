import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        boolean swapped = true;
        int n = arr.length;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    T temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        quickSort(arr, 0, arr.length, comparator, rand);
    }

    /**
     * helper method for quickSort
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param a the index of the beginning of a sublist
     * @param b the index of the end of a sublist
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, int a, int b,
                                     Comparator<T> comparator, Random rand) {
        if (a >= b - 1) {
            return;
        }

        int i = a + 1;
        int j = b - 1;
        int pivotIndex = rand.nextInt(j - a) + a;
        T pivot = arr[pivotIndex];
        arr[pivotIndex] = arr[a];
        arr[a] = pivot;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivot) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivot) >= 0) {
                j--;
            }
            if (i < j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        //swap pivot and arr[j]
        arr[a] = arr[j];
        arr[j] = pivot;
        quickSort(arr, a, j, comparator, rand);
        quickSort(arr, j + 1, b, comparator, rand);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }

        //if length is odd, left list is smaller than right
        T[] left = (T[]) new Object[arr.length / 2];
        T[] right = (T[]) new Object[arr.length - (arr.length / 2)];
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length / 2) {
                left[i] = arr[i];
            } else {
                right[i - (arr.length / 2)] = arr[i];
            }
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(arr, left, right, comparator);
    }

    /**
     * function to merge two sublists
     *
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param left the left sublist
     * @param right the right sublist
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void merge(T[] arr, T[] left,
            T[] right, Comparator<T> comparator) {
        int i = 0;
        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length) {
            if (comparator.compare(left[l], right[r]) <= 0) {
                arr[i] = left[l];
                i++;
                l++;
            } else {
                arr[i] = right[r];
                i++;
                r++;
            }
        }
        while (l < left.length) {
            arr[i] = left[l];
            i++;
            l++;
        }
        while (r < right.length) {
            arr[i] = right[r];
            i++;
            r++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array cannot be null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        if (arr.length == 1) {
            return arr;
        }

        int digits = 0;
        while ((arr[0] / (pow(10, 0)) != 0 && (digits < 31))) {
            digits++;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] / pow(10, digits) != 0) {
                while ((arr[i] / (pow(10, digits)) != 0 && (digits < 31))) {
                    digits++;
                }
            }
        }
        int base = 10;

        LinkedList<Integer>[] buckets = (LinkedList<Integer>[])
            new LinkedList[(2 * base) - 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int index = 0;
        for (int i = 0; i < digits; i++) {
            for (int j = 0; j < arr.length; j++) {
                index = ((arr[j] / (pow(base, i))) % 10) + (base - 1);
                buckets[index].addLast(arr[j]);
            }

            int pos = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (buckets[j].size() != 0) {
                    arr[pos] = buckets[j].removeFirst();
                    pos++;
                }
            }
        }
        return arr;
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array cannot be null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int digits = 0;
        while (max > 0) {
            max = max / 10;
            digits++;
        }

        return toArray(msdRadixSort(arr, pow(10, digits)));
    }

    /**
     * Helper method for msdRadixSort
     *
     * @param arr the array to be sorted
     * @param exp the number of digits
     * @return the sorted array as an ArrayList
     */
    public static ArrayList<Integer> msdRadixSort(int[] arr, int exp) {
        ArrayList<Integer>[] buckets = new ArrayList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int num : arr) {
            buckets[((num / exp) % 10) + 9].add(num);
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        for (ArrayList<Integer> list : buckets) {
            if (list.size() > 1 && exp >= 10) {
                list = msdRadixSort(toArray(list), exp / 10);
            }
            for (int num : list) {
                sorted.add(num);
            }
        }
        return sorted;
    }

    /**
     * A function to turn an ArrayList to an int array
     *
     * @param list the list to turn into an array
     * @return an array containing all the elements of list
     */
    public static int[] toArray(ArrayList<Integer> list) {
        int[] result = new int[list.size()];
        int i = 0;
        for (int x : list) {
            result[i] = x;
            i++;
        }
        return result;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
