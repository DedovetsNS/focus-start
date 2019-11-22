package collections.impl.ArrayList;

import collections.Iterable;
import collections.Iterator;
import collections.List;
import java.util.Arrays;

public class ArrayList<E> implements List<E>, Iterable<E> {
    private int size =0;
    private  int capacity = 10;
    private Object[] nestedArray = new Object[10];

    public int getSize() {
        return size;
    }

    private void ensureCapacity(){
        capacity = ((capacity*3)/2)+1;
        nestedArray = Arrays.copyOf(nestedArray,capacity);
    }

    @Override
    public boolean add(E e) {
        if(size == capacity){
            this.ensureCapacity();
        }
        nestedArray[size]=e;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        Object removeElement = nestedArray[index];
        Object[] oldNestedArray = nestedArray;
        nestedArray[size]=null;
        size--;
        if(index!=0) {
            System.arraycopy(oldNestedArray, 0, nestedArray, 0, index - 1);
            System.arraycopy(oldNestedArray, index + 1, nestedArray, index, size - index);
        }else{
            System.arraycopy(oldNestedArray, 1, nestedArray, 0, size);
        }

        return (E) removeElement;
    }

    @Override
    public E get(int index) {
        return (E) nestedArray[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator(this);
    }
}
