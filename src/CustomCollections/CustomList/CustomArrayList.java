package CustomCollections.CustomList;

import java.util.Arrays;
import java.util.Collection;

/**
 * @param <T>
 * @author VaheAvetikyan
 */
public class CustomArrayList<T> implements CustomList<T> {

    /**
     * Shared empty array instance used for empty instances.
     */
    private final Object[] EMPTY_ARRAY = {};

    /**
     * The array buffer
     */
    private Object[] dataArray;

    /**
     * The size of the ArrayList.
     */
    private int size;

    /**
     * Constructs an empty list.
     */

    public CustomArrayList() {
        this.dataArray = EMPTY_ARRAY;
        this.size = 0;
    }

    /**
     * Constructs a list with the elements of specified collection.
     */
    public CustomArrayList(Collection<? extends T> initialArray) {
        this.dataArray = initialArray.toArray();
        this.size = dataArray.length;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if list is empty, false otherwise
     *
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if a certain value is in the list
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * @param numberOfElements is added to the size of list if it exceeds current array length,
     *                         else the size is doubled.
     * @return new Object array
     */

    private Object[] grow(int numberOfElements) {
        if (this.size + numberOfElements > this.dataArray.length) {
            return this.dataArray = Arrays.copyOf(this.dataArray, Math.max(this.size * 2, this.size + numberOfElements));
        }
        return this.dataArray;
    }

    private Object[] grow() {
        return grow(1);
    }


    private boolean add(T t, Object[] data, int s) {
        if (s == this.dataArray.length) {
            this.dataArray = grow();
        }
        this.dataArray[s] = t;
        size++;
        return true;
    }

    /**
     * Adds element to end
     */
    @Override
    public boolean add(T t) {
        add(t, dataArray, size);
        return true;
    }

    /**
     * Adds element at index and moves the rest toward the end
     */
    @Override
    public boolean add(int index, T t) {
        this.dataArray = grow(1);
        this.size++;
        if (index >= 0 && index < this.size) {
            T temp = set(index, t);
            for (int i = index + 1; i < this.size; i++) {
                temp = set(i, temp);
            }
        }
        return true;
    }

    /**
     * Removes element at index and moves the rest toward the front
     */
    @Override
    public T remove(int index) {
        T temp = null;
        if (index >= 0 && index < this.size) {
            temp = (T) this.dataArray[index];
            for (int i = index; i < this.size - 1; i++) {
                set(i, (T) this.dataArray[i + 1]);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.dataArray[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public void remove(T t) {
        remove(indexOf(t));
    }

    /**
     * Discards all elements of the list
     */
    @Override
    //    public void clear() {
    //        Object[] tempArray = this.dataArray;
    //        while (size > 0) {
    //            size--;
    //            tempArray[size] = null;
    //        }
    //    }
    public void clear() {
        this.dataArray = EMPTY_ARRAY;
        this.size = this.dataArray.length;
    }

    /**
     * Returns element at @param index
     *
     * @return element at @param index
     */
    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) dataArray[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Replaces element at index and returns original
     */
    @Override
    public T set(int index, T t) {
        Object temp = null;
        if (index >= 0 && index < this.size) {
            temp = dataArray[index];
            this.dataArray[index] = t;
        }
        return (T) temp;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, this.size);
    }

    /**
     * Returns last matching index or -1 if not found
     */
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, this.size);
    }

    private int indexOfRange(Object o, int start, int end) {
        Object[] newRef = this.dataArray;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (newRef[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(newRef[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int lastIndexOfRange(Object o, int start, int end) {
        Object[] newRef = this.dataArray;
        if (o == null) {
            for (int i = end - 1; i > start; i--) {
                if (newRef[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i > start; i--) {
                if (o.equals(newRef[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(dataArray[i]).append(" ");
        }
        return s.toString();
    }
}