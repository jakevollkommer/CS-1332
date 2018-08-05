import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial length of {@code INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if ((size + 1) >= backingArray.length) {
            resizeBackingArray();
        }
        backingArray[size + 1] = item;
        upHeapify(size + 1);
        size++;
    }

    /**
     * Method to resize the backing array to twice the current capacity
     * plus one.
     */
    public void resizeBackingArray() {
        T[] newArray = (T[]) new Comparable[(backingArray.length * 2) + 1];
        for (int i = 1; i < (size + 1); i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }

    /**
     * Method to up heapify after adding a new item
     *
     * @param index the index of the current item to heapify
     */
    public void upHeapify(int index) {
        if (index < 2) {
            return;
        }
        int parent = index / 2;
        T data = backingArray[index];
        T parentData = backingArray[parent];
        if (data.compareTo(parentData) > 0) {
            backingArray[parent] = data;
            backingArray[index] = parentData;
            upHeapify(parent);
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty heap");
        }
        T removed = backingArray[1];
        size--;
        backingArray[1] = backingArray[size + 1];
        backingArray[size + 1] = null;
        downHeapify(1);
        return removed;
    }

    /**
     * Method to down heapify after removing the root
     *
     * @param index the index of the current item to heapify
     */
    public void downHeapify(int index) {
        int leftChild = index * 2;
        int rightChild = (index * 2) + 1;
        if (leftChild >= (size + 1)) {
            return;
        }
        T data = backingArray[index];
        T leftData = backingArray[leftChild];
        if (rightChild >= (size + 1)) {
            if (data.compareTo(leftData) >= 0) {
                return;
            } else {
                backingArray[index] = backingArray[leftChild];
                backingArray[leftChild] = data;
                return;
            }
        }
        T rightData = backingArray[rightChild];
        if (data.compareTo(leftData) >= 0 && data.compareTo(rightData) >= 0) {
            return;
        }
        int biggerChild = rightChild;
        if (leftData.compareTo(rightData) > 0) {
            biggerChild = leftChild;
        }
        backingArray[index] = backingArray[biggerChild];
        backingArray[biggerChild] = data;
        downHeapify(biggerChild);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
