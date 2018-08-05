/**
 * Your implementation of an ArrayList.
 *
 * @author Jake Vollkommer
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add to negative index");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot add at index > size");
        }
        if (size == INITIAL_CAPACITY) {
            resizeBackingArray();
        }
        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[index] = data;
        size++;
    }

    @Override
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot remove index < 0");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove index >= size");
        }
        T data = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        backingArray[size] = null;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(size - 1);
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot get index < 0");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot get index > size");
        }
        return backingArray[index];
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
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Resize the backing array to twice its size.
     */
    public void resizeBackingArray() {
        T[] temp = backingArray;
        T[] newArray = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = temp[i];
        }
        backingArray = newArray;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
