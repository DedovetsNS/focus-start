package collections.impl;

import collections.impl.LinkedList.LinkedList;
import collections.impl.LinkedList.LinkedListIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LinkedListTest {

    private LinkedList<Integer> testList;
    private Integer result;

    @Before
    public void init() {
        testList = new LinkedList<>();

    }
    // В моем понимании, по правильному тестировать этот метод, это добавить и через рефлексию залезть
    // в экземпляр списка и посмотреть значение поля value какого-нибудь Node-а, пока не получилось

//    @Test
//    public void addTestWithReflection() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        testList.add(1);
//        testList.add(2);
//        testList.add(3);
//        testList.add(4);
//
//        Class listClass = testList.getClass();
//        Method getHead = listClass.getDeclaredMethod("getHead");
//        getHead.setAccessible(true);
//       Class node = getHead.invoke(testList).getClass();
//       Field next =  node.getDeclaredField("next");
//       next.setAccessible(true);
//        Field value =  node.getDeclaredField("value");
//        next.setAccessible(true);
//        Integer result = (Integer) value.get(next.get(next.get(getHead.invoke(testList))));
//        assertEquals(java.util.Optional.ofNullable(result), 3);
//    }

    @Test
    public void addTest()  {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        result = testList.get(3);
        assertEquals(Long.valueOf(result),Long.valueOf(3));
    }

    @Test
    public void addWithIndexTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(555,3);
        result = testList.get(3);
        assertEquals(Long.valueOf(result),Long.valueOf(555));
        result = testList.get(4);
        assertEquals(Long.valueOf(result),Long.valueOf(3));
    }

    @Test
    public void removeTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.remove(3);
        result = testList.get(3);
        assertEquals(Long.valueOf(result),Long.valueOf(4));
    }

    @Test
    public void getTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        result = testList.get(3);
        assertEquals(Long.valueOf(result),Long.valueOf(3));
    }

    @Test
    public void iteratorTest() throws NoSuchFieldException, IllegalAccessException {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        LinkedListIterator iter = (LinkedListIterator) testList.iterator();

        while (iter.hasNext()){
            Field value = iter.next().getClass().getDeclaredField("value");
            value.setAccessible(true);
           result = (Integer) value.get(iter.next());
        }
        assertEquals(Long.valueOf(result),Long.valueOf(4));
    }
}