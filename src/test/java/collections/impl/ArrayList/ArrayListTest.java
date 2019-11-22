package collections.impl.ArrayList;

import collections.Iterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArrayListTest {

    private ArrayList<Integer> testList;

    @Before
    public void init() {
        testList = new ArrayList<>();
        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
    }

    @Test
    public void getSizeTest() {
        assertEquals(testList.getSize(),4);
    }

    @Test
    public void addTest() {
        int oldSize = testList.getSize();
        testList.add(4);
        assertEquals(java.util.Optional.of(testList.get(oldSize)), java.util.Optional.of(4));
    }

    @Test
    public void removeTest() {
        testList.remove(2);
        assertEquals(java.util.Optional.of(testList.get(2)), java.util.Optional.of(3));
        testList.remove(0);
        assertEquals(java.util.Optional.of(testList.get(1)), java.util.Optional.of(3));
    }

    @Test
    public void getTest() {
        assertEquals(java.util.Optional.of(testList.get(2)), java.util.Optional.of(2));
    }

    @Test
    public void iteratorTest() {
        Iterator iter = testList.iterator();
        Integer result;
        int i =0;
        while (iter.hasNext()==true){
           result = (Integer) iter.next();
            assertEquals(result,testList.get(i));
            i++;
        }
    }
}