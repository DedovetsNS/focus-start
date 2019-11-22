package collections.impl.LinkedList;

import collections.Iterable;
import collections.Iterator;
import collections.List;

public class LinkedList<E> implements List<E>, Iterable<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    Node<E> getHead() {
        return head;
    }

    @Override
    public boolean add(E e) {
        if(head == null){
            head = new Node<>(e);
            tail = head;
            size++;
            return true;
        }
        else{
            Node newNode = new Node<>(e);
            tail.setNext(newNode);
            newNode.setPrevios(tail);
            tail = newNode;
            size++;
            return true;
        }
    }

    public boolean add(E e, int index) {
        Node oldInIndex = (Node) this.getNode(index);
        Node newNode = new Node (e);
        oldInIndex.getPrevios().setNext(newNode);
        newNode.setNext(oldInIndex);
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        Node oldInIndex = (Node) this.getNode(index);
        oldInIndex.getPrevios().setNext(oldInIndex.getNext());
        oldInIndex.getNext().setPrevios(oldInIndex.getPrevios());
        size--;
        return (E) oldInIndex;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(index<1 || index>size){
            throw new IndexOutOfBoundsException();
        }
        Node getNode = this.head;
        LinkedListIterator iter = (LinkedListIterator) this.iterator();
        for (int i = 0; i < index ; i++) {
            getNode = (Node) iter.next();
        }
        return (E) getNode.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(this);
    }

    private E getNode(int index) throws IndexOutOfBoundsException {
        if(index<1 || index>size){
            throw new IndexOutOfBoundsException();
        }
        Node getNode = this.head;
        LinkedListIterator iter = (LinkedListIterator) this.iterator();
        for (int i = 0; i < index ; i++) {
            getNode = (Node) iter.next();
        }
        return (E) getNode;
    }

}
