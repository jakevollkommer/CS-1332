import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of HashMap.
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        size++;
        if (((float) size / table.length) >= MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % table.length;
        int ogindex = index;
        int putIndex = index;
        boolean shouldReplace = false;
        int i = 1;
        //Quadratic probe until we find an empty spot
        while (table[index] != null) {
            MapEntry<K, V> current = table[index];
            if (current.isRemoved()) {
                putIndex = index;
                shouldReplace = true;
            }
            if (current.getKey().equals(key)) {
                if (!current.isRemoved()) {
                    oldValue = table[index].getValue();
                    size--;
                }
                MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
                table[index] = newEntry;
                return oldValue;
            }
            index = (ogindex + (i * i)) % table.length;
            if (i == table.length) {
                resizeBackingTable(table.length * 2 + 1);
                index = Math.abs(key.hashCode()) % table.length;
                i = 1;
            }
            i++;
        }
        MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
        if (shouldReplace) {
            table[putIndex] = newEntry;
            return null;
        }
        table[index] = newEntry;
        return oldValue;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        int ogindex = index;
        int i = 1;
        while (table[index] == null || !table[index].getKey().equals(key)) {
            index = (ogindex + (i * i)) % table.length;
            if (i == table.length) {
                throw new NoSuchElementException("Key is not in the map");
            }
            i++;
        }
        V value = table[index].getValue();
        table[index].setRemoved(true);
        size--;
        return value;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        int ogindex = index;
        int i = 1;
        while (table[index] == null || !table[index].getKey().equals(key)) {
            index = (ogindex + (i * i)) % table.length;
            if (i == table.length) {
                throw new NoSuchElementException("Key not in map");
            }
            i++;
        }
        return table[index].getValue();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        int ogindex = index;
        int i = 1;
        while (table[index] == null || !table[index].getKey().equals(key)) {
            index = (ogindex + (i * i)) % table.length;
            if (i == table.length) {
                return false;
            }
            i++;
        }
        if (table[index].isRemoved()) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                set.add(table[i].getKey());
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> vals = new ArrayList<V>();
        for (K key: keySet()) {
            vals.add(get(key));
        }
        return vals;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < 0) {
            String message = "Cannot make a table of negative size";
            throw new IllegalArgumentException(message);
        }
        if (length < size) {
            String message = "Cannot make a table smaller than previous size";
            throw new IllegalArgumentException(message);
        }
        MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> current = table[i];
                int index = Math.abs(current.getKey().hashCode()) % length;
                int ogindex = index;
                int j = 1;
                while (temp[index] != null) {
                    index = (ogindex + (j * j)) % length;
                    if (j >= length) {
                        resizeBackingTable(2 * length + 1);
                        return;
                    }
                    j++;
                }
                temp[index] = current;
            }
        }
        table = temp;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
