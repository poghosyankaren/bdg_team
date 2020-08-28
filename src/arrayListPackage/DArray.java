package arrayListPackage;

import java.util.AbstractList;
import java.util.List;

public class DArray<E> extends AbstractList<E> implements List<E> {
    private int size;
    private static final int DEFAULT_SIZE = 235;
    private Object[] array;

    public DArray() {
        array = new Object[DEFAULT_SIZE];
    }


    public DArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Capacity can not be negative " + " given capacity is " + length);
        }
        array = new Object[size];
    }

    public DArray(Object[] newArr) {
        for (int i = 0; i < newArr.length; i++) {
            this.array[i] = newArr[i];
        }
        size = newArr.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();

        array[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        this.remove(index);
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
            size = 0;
        }
    }

    @Override
    public E get(int index) {
        validate(index);
        return (E) array[index];
    }

    @Override
    public E set(int index, E element) {
        validate(index);
        E prevEl = (E) array[index];
        array[index] = element;
        return prevEl;
    }

    @Override
    public void add(int index, E element) {
        if (size == array.length) {
            ensureCapacity();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        validate(index);
        E removeElement = (E) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        this.size--;
        return removeElement;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i].equals(o)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    // public void addAll(E[] newArr) {
    //    ensureCapacity(newArr.length);
    //   for (int i = 0;i < newArr.length;i++){
    //       this.array[size++] = newArr[i];
    //   }
    // }


    //  public void addByCount(E[] newArr, int index, int count) {
    //   int elementCount = (newArr.length - index) > count?count:newArr.length - index;
    //  ensureCapacity(elementCount);
    // for (int i = index;i < elementCount + index;i++){
    //    this.array[size++] = newArr[i];
    //  }
    //  }


    // public void addAllByPosition(int position, E[] newArr) {
    //   ensureCapacity(newArr.length);
    //  for (int i = size+newArr.length-1;i >= position+newArr.length;i--){
    //       this.array[i] = this.array[i - newArr.length];
    //   }
    //   for (int i = 0;i < newArr.length;i++){
    //     this.array[position++] = newArr[i];
    // }
    //this.size+=newArr.length;
    // }

    private void ensureCapacity() {
        if (size == array.length) {
            int length = array.length * 3 / 2;
            Object[] newArr = new Object[length + 1];
            System.arraycopy(array, 0, newArr, 0, array.length);
            this.array = newArr;
        }
    }

    private void ensureCapacity(int count) {
        if (size + count >= array.length) {
            int length = (size + count) * 3 / 2;
            Object[] newArr = new Object[length + count + 1];
            System.arraycopy(array, 0, newArr, 0, array.length);
            this.array = newArr;

        }
    }

    public void validate(int index) {
        isSizeValid(index, false);
    }

    private void isSizeValid(int index, boolean validation) {
        int upperBound = validation ? size : size - 1;
        if (index < 0 || upperBound > array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

}
