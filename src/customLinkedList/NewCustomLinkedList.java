package customLinkedList;

import java.util.*;
import java.util.List;

public class NewCustomLinkedList<T> extends AbstractList<T> implements List<T>  {

    private int size;
    private NewCustomLinkedList.Node<T> head;
    private NewCustomLinkedList.Node<T> tail;
    private static final long serialVersionUID = 876323262645176374L;

    public NewCustomLinkedList() {
    }

    public NewCustomLinkedList(List<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T t) {
        NewCustomLinkedList.Node newElement = new NewCustomLinkedList.Node(t);
        if (this.head == null) {
            this.head = newElement;
        } else {
            newElement.previous = this.tail;
            this.tail.next = newElement;
        }
        this.tail = newElement;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Given index out of array's bound");
        }
        if (index == size) {
            this.add(element);
            return;
        }
        NewCustomLinkedList.Node newElement = new NewCustomLinkedList.Node(element);
        NewCustomLinkedList.Node oldElement;
        oldElement = this.head;
        for (int i = 0; i <= index; i++) {
            oldElement = this.head.next;
        }
        newElement.previous = oldElement.previous;
        newElement.next = oldElement;
        if (oldElement == this.head) {
            this.head = newElement;
        } else {
            oldElement.previous.next = newElement;
        }
        oldElement.previous = newElement;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return (T) getNodeByIndex(index).item;
    }

    @Override
    public T set(int index, T element) {
        checkIndexRange(index);
        NewCustomLinkedList.Node replacingElement = getNodeByIndex(index);
        replacingElement.item = element;
        return (T) replacingElement.item;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        NewCustomLinkedList.Node removingElement = getNodeByIndex(index);
        if (removingElement == this.head) {
            if(size != 1){
                this.head = removingElement.next;
                removingElement.next.previous = null;
            }else{
                this.head = null;
            }
        } else if (removingElement == this.tail) {
            this.tail = removingElement.previous;
            removingElement.previous.next = null;
        } else {
            removingElement.previous.next = removingElement.next;
            removingElement.next.previous = removingElement.previous;
        }
        size--;
        return (T) removingElement.item;
    }

    @Override
    public boolean remove(Object object) {
        NewCustomLinkedList.Node removingElement = new NewCustomLinkedList.Node((T) object);
        NewCustomLinkedList.Node el = this.head;
        int index = 0;
        while (el != null) {
            if (el.item == removingElement.item) {
                remove(index);
                return true;
            }
            el = el.next;
            index++;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        NewCustomLinkedList.Node el = this.head;
        while (el != null){
            stringBuilder.append(el.item);
            if(el != this.tail){
                stringBuilder.append(", ");
            }
            el = el.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private NewCustomLinkedList.Node<T> getNodeByIndex(int index) {
        checkIndexRange(index);
        NewCustomLinkedList.Node element;
        if (index == 0) {
            element = this.head;
        } else if (index == size - 1) {
            element = this.tail;
        } else if (index <= size / 2) {
            element = this.head;
            for (int i = 0; i <= index; i++) {
                element = element.next;
            }
        } else {
            element = this.tail;
            for (int i = size - 1; i > index; i--) {
                element = element.previous;
            }
        }
        return element;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Given index out of array's bound");
        }
    }

    private static class Node<T> {
        T item;
        NewCustomLinkedList.Node<T> previous;
        NewCustomLinkedList.Node<T> next;

        Node(NewCustomLinkedList.Node<T> var1, T item, NewCustomLinkedList.Node<T> var2) {
            this.previous = var1;
            this.item = item;
            this.next = var2;
        }

        Node(T item) {
            this.item = item;
        }
    }
}
