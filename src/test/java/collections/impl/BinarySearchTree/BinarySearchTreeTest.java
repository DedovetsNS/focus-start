package collections.impl.BinarySearchTree;

import collections.Iterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer,String> testTree = new BinarySearchTree<>();

    @Before
    public void init(){
        testTree.add(40,"40");
        testTree.add(30,"30");
        testTree.add(50,"50");
        testTree.add(45,"45");
        testTree.add(55,"55");
        testTree.add(25,"25");
        testTree.add(35,"35");

    }

    @Test
    public void addTest() {
        testTree.add(60,"60");
        String result = (String) testTree.getNode(55).getRight().getValue();
        assertEquals(result,"60");
    }

    @Test
    public void getTest() {
        String result = (String) testTree.get(55);
        assertEquals(result,"55");
    }

    @Test
    public void deleteTest() {
        testTree.delete(30);
        assertEquals(testTree.getRoot().getLeft().getValue(),"25");
        assertEquals(testTree.getNode(25).getLeft(),null);
    }

    @Test
    public void iteratorTest() {
        Iterator iter =  spy(testTree.iterator());
        Node testNode = new Node();
        while (iter.hasNext()){
            testNode= (Node) iter.next();
        }
        assertEquals(testNode.getValue(),"55");
        verify(iter,times(7)).next();
    }

    @Test
    public void reverseTest() {
        testTree.reverse();
        Iterator iter =  spy(testTree.iterator());
        Node testNode = new Node();
        while (iter.hasNext()){
            testNode= (Node) iter.next();
        }
        assertEquals(testNode.getValue(),"25");
    }
}