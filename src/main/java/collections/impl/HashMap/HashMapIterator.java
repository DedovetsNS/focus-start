package collections.impl.HashMap;

import collections.Iterator;
import collections.impl.ArrayList.ArrayList;
import collections.impl.ArrayList.ArrayListIterator;

public class HashMapIterator implements Iterator<Entry> {

    private Entry currentEntry;
    private int mapCapacity;
    private ArrayList listFromMap;
    private ArrayListIterator listIter;

    HashMapIterator(HashMap map) {
        this.mapCapacity=map.getCapacity();
        this.listFromMap=mapToList(map);
        this.listIter = (ArrayListIterator) listFromMap.iterator();
    }

    private ArrayList mapToList(HashMap map){
        ArrayList listFromMap = new ArrayList();
        for (int i = 0; i < mapCapacity; i++) {
            currentEntry=map.getEntrybyIndex(i);
            if(currentEntry==null){
                continue;
            }
            while (currentEntry!=null) {
                listFromMap.add(currentEntry);
                currentEntry=currentEntry.getNext();
            }
        }
        return listFromMap;
    }

    @Override
    public boolean hasNext() {
        return listIter.hasNext();
        }

    @Override
    public Entry next() {
        return (Entry) listIter.next();
    }
}
