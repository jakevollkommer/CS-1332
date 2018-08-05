import java.util.NoSuchElementException;

/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add to negative index");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot add beyond size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (index == 0) {
            addToFront(data);
            return;
        } else if (index == size) {
            addToBack(data);
            return;
        }
        LinkedListNode<T> curr = head;
        LinkedListNode<T> next;
        for (int i = 1; i < index; i++) {
            curr = curr.getNext();
        }
        next = curr.getNext();
        curr.setNext(newNode);
        newNode.setNext(next);
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot remove negative index");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove beyond size");
        }
        T data;
        if (index == 0) {
            return removeFromFront();
        } else if (index == (size - 1)) {
            return removeFromBack();
        } else {
            LinkedListNode<T> curr = head;
            LinkedListNode<T> prev = null;
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            data = (T) curr.getData();
            prev.setNext(curr.getNext());
        }
        size--;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T data = (T) head.getData();
        if (size == 1) {
            clear();
            return data;
        }
        head = head.getNext();
        size--;
        return data;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        T data = (T) tail.getData();
        if (size == 1) {
            clear();
            return data;
        }
        LinkedListNode<T> curr = head;
        LinkedListNode<T> prev = null;
        while (curr.getNext() != tail) {
            curr = curr.getNext();
        }
        curr.setNext(null);
        tail = curr;
        size--;
        return data;
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        LinkedListNode<T> curr = head;
        if (curr.getData().equals(data)) {
            return removeFromFront();
        }
        int i = 0;
        while (curr.getNext() != null) {
            curr = curr.getNext();
            i++;
            if (curr.getData().equals(data)) {
                return removeAtIndex(i);
            }
        }
        throw new NoSuchElementException("Data not contained in the list");
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot get negative index");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot get index > size");
        }
        LinkedListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        T data = (T) curr.getData();
        return data;
    }

    @Override
    public Object[] toArray() {
        Object[] toReturn = new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            toReturn[i] = current.getData();
            current = current.getNext();
        }
        return toReturn;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
