package collections.impl.HashMap;

import collections.Iterable;
import collections.Iterator;
import collections.Map;

import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V>, Iterable {
    private float loadFactor = 0.75f;
    private int size = 0;
    private int capacity = 16;
    private Entry[] nestedArray =new Entry[capacity];
    private Entry nullEntry = new Entry(null,null);

    Entry getEntrybyIndex(int index){
        return nestedArray[index];
    }

    int getCapacity() {
        return capacity;
    }

    private int getIndexForKey(K key){
        int keyHash = key.hashCode();
        return keyHash%(capacity-1);
    }

    private void resizeAndTransfer() {
        HashMapIterator iter = (HashMapIterator) this.iterator();
        int newCapacity = capacity*2;
        nestedArray = new Entry[newCapacity];
        capacity=newCapacity;
        size=0;
        while (iter.hasNext()){
            Entry entry = iter.next();
            K key = (K) entry.getKey();
            V value = (V) entry.getValue();
            this.insert(key,value);
        }
    }

    private Entry findEntryInNestedArrayByKey (K key){
        Entry findEntry = getEntrybyIndex(getIndexForKey(key));
        if(findEntry==null){
            return findEntry;
        }
        while(!findEntry.getKey().equals(key)){
            findEntry=findEntry.getNext();
            if(findEntry==null){
                return findEntry;
            }
        }
        return findEntry;
    }

    private void insertAtNestedArray(Entry entry, int index){
        if(nestedArray[index]==null){
            nestedArray[index]=entry;
            size++;
        }
        else {
            Entry oldEntry = nestedArray[index];
            nestedArray[index]=entry;
            entry.setNext(oldEntry);
            oldEntry.setPrevious(entry);
        }
    }

    @Override
    public boolean insert(K key, V value) {
        if(key==null){
            nullEntry.setValue(value);
            return true;
        }
        else if(size>=capacity*loadFactor){
            resizeAndTransfer();
        }
        else {
            if(findEntryInNestedArrayByKey(key)!=null){
                findEntryInNestedArrayByKey(key).setValue(value);
                return true;
            }
            else {
                Entry insertEntry = new Entry<>(key,value);
                insertAtNestedArray(insertEntry,getIndexForKey(key));
            }
        }
        return true;
    }

   @Override
     public V get(K key) {
         if(key==null&&nullEntry.getValue()==null){
             throw new NoSuchElementException();
         }
       if(key==null){
           return (V) nullEntry.getValue();
       }
         Entry getEntry = findEntryInNestedArrayByKey(key);
         if(getEntry==null){
             throw new NoSuchElementException();
         }
         return (V) getEntry.getValue();
     }

    @Override
    public Entry delete(K key) {
        if(key==null){
            nullEntry.setValue(null);
            return nullEntry;
        }
        Entry deleteEntry = findEntryInNestedArrayByKey(key);
        if(deleteEntry.getValue()==null){
            throw new NoSuchElementException();
        }
        if(deleteEntry.getPrevious()==null){
            nestedArray[getIndexForKey(key)]=deleteEntry.getNext();
        }
        if(deleteEntry.getNext()!=null&&deleteEntry.getPrevious()!=null){
            deleteEntry.getPrevious().setNext(deleteEntry.getNext());
            deleteEntry.getNext().setPrevious(deleteEntry.getPrevious());
        }
        if(deleteEntry.getNext()==null){
            deleteEntry.getPrevious().setNext(null);
        }
        return  deleteEntry;
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator(this);
    }
}
