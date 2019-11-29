package collections.impl.BinarySearchTree;

import collections.Iterator;
import collections.impl.ArrayList.ArrayList;

public class BinarySerchTreeIterator <K extends Comparable,V> implements Iterator {

    private ArrayList<Node<K,V>> nestedList = new ArrayList<>();
    private Iterator<Node<K,V>> listIter;

    BinarySerchTreeIterator(BinarySearchTree<K, V> tree) {
        addToList(tree.getRoot());
        listIter = nestedList.iterator();
    }

    private void addToList(Node<K,V> node){
        if(node==null){
            return;
        }
        nestedList.add(node);
        addToList(node.getLeft());
        addToList(node.getRight());
    }

    @Override
    public boolean hasNext() {
        return listIter.hasNext();
    }

    @Override
    public Object next() {
        return listIter.next();
    }
}








