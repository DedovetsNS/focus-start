package collections.impl.LinkedList;

import collections.Iterator;

public class LinkedListIterator<E> implements Iterator<E> {

   private Node<E> currentPoin;
   private Node<E> getPoint;

    LinkedListIterator(LinkedList list){
        this.currentPoin = list.getHead();
    }

    @Override
    public boolean hasNext() {
        if(currentPoin==null){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public E next() {
        getPoint = currentPoin;
        currentPoin=currentPoin.getNext();
        return (E) getPoint;
    }
}
