package collections.impl.ArrayList;

import collections.Iterator;

public class ArrayListIterator<E> implements Iterator {
    private ArrayList list;
    private E currentPoin;
    private E getPoint;
    private int count = 0;
    private int listSize;

    ArrayListIterator(ArrayList list) {
        this.list = list;
        currentPoin = (E) list.get(0);
        this.listSize = list.getSize();
    }
    @Override
    public boolean hasNext() {
        if(count>=listSize){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public E next() {
        getPoint = currentPoin;
        count++;
        currentPoin= (E) list.get(count);
        return (E) getPoint;
    }
}
