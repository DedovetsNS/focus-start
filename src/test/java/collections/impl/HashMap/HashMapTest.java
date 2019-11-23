package collections.impl.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HashMapTest {

    private HashMap testHashMap = new HashMap();

    @Before
    public void init() {
        testHashMap = new HashMap();
        testHashMap.insert(0,"zero");
        testHashMap.insert(1,"one");
        testHashMap.insert(2,"two");
        testHashMap.insert(3,"three");
        testHashMap.insert(4,"four");
    }

    @Test
    public void getEntrybyIndexTest() {
        Entry entry = testHashMap.getEntrybyIndex(3);
        assertEquals(entry.getKey(),3);
        assertEquals(entry.getValue(),"three");
    }

    @Test
    public void getCapacityTest() {
        assertEquals(testHashMap.getCapacity(),16);
    }

    @Test
    public void insertTest() {
        testHashMap.insert(null,"null");
        testHashMap.insert(3,"new three");
        testHashMap.insert(7,"seven");
        assertEquals(testHashMap.get(null),"null");
        assertEquals(testHashMap.get(3),"new three");
        assertEquals(testHashMap.get(7),"seven");
    }

    @Test(expected = NoSuchElementException.class)
    public void getTest() {
        testHashMap.insert(4,"new four");
        assertEquals(testHashMap.get(null),null);
        assertEquals(testHashMap.get(1),"one");
        assertEquals(testHashMap.get(4),"new four");
        when(testHashMap.get(20)).thenThrow(NoSuchElementException.class);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteTest() {
        testHashMap.insert(19,"new four");
        testHashMap.delete(19);
        when(testHashMap.get(19)).thenThrow(NoSuchElementException.class);
        assertEquals(testHashMap.get(4),"four");
        testHashMap.insert(null,"new null");
        testHashMap.delete(null);
        when(testHashMap.get(null)).thenThrow(NoSuchElementException.class);
        testHashMap.insert(16,"second in first");
        testHashMap.insert(31,"third in first");
        testHashMap.insert(46,"fourth in first");
        testHashMap.delete(46);
        assertEquals(testHashMap.getEntrybyIndex(1).getValue(),"third in first");
        testHashMap.insert(46,"fourth in first");
        testHashMap.delete(31);
        assertEquals(testHashMap.getEntrybyIndex(1).getValue(),"fourth in first");
    }

    @Test
    public void iteratorTest() {
        testHashMap.insert(16,"second in first");
        testHashMap.insert(31,"third in first");
        testHashMap.insert(46,"fourth in first");
       HashMapIterator iter = (HashMapIterator) testHashMap.iterator();
       int count = 0;
       Entry testEntry=null;
       while (iter.hasNext()){
           count++;
           testEntry=iter.next();
       }
       assertEquals(count,8);
       assertEquals(testEntry.getValue(),"four");
    }
}