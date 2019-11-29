package collections.impl.BinarySearchTree;

import collections.Iterable;
import collections.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable,V> implements Iterable {

    private Node<K,V> root;

    public void add(K key, V value) {
        Node<K,V> insertNode = new Node<>(key,value);
        Node<K,V> currentNode;
        Node<K,V> previousNode;

        if(root==null){
            root=insertNode;
        }
        else {
            currentNode = root;
            while (true){
                if (insertNode.getKey().compareTo(currentNode.getKey())>0){
                    previousNode=currentNode;
                    currentNode=currentNode.getRight();
                    if(currentNode==null){
                        previousNode.setRight(insertNode);
                        insertNode.setParent(previousNode);
                        return;
                    }
                }
                else if (insertNode.getKey().compareTo(currentNode.getKey())<0){
                    previousNode=currentNode;
                    currentNode=currentNode.getLeft();
                    if(currentNode==null){
                        previousNode.setLeft(insertNode);
                        insertNode.setParent(previousNode);
                        return;
                    }
                }
                else if (insertNode.getKey().compareTo(currentNode.getKey())==0){
                    currentNode.setValue(value);
                    return;
                }
            }
        }
    }

    public Node<K,V> getNode(K key) {
        Node<K,V> currentNode;

        if(root==null){
            throw new NoSuchElementException("this is an empty tree");
        }
        else {
            currentNode = root;
            while (true){
                if (key.compareTo(currentNode.getKey())>0){
                    currentNode=currentNode.getRight();
                    if(currentNode==null){
                        throw new NoSuchElementException("item with key:" +key.toString()+" not found");
                    }
                }
                else if (key.compareTo(currentNode.getKey())<0){
                    currentNode=currentNode.getLeft();
                    if(currentNode==null){
                        throw new NoSuchElementException("item with key:" +key.toString()+" not found");
                    }
                }
                else if (key.compareTo(currentNode.getKey())==0){
                    return currentNode;
                }
            }
        }
    }

    public V get(K key) {
        return getNode(key).getValue();
    }

    private void deleteLeaf(K key){
        Node<K,V> deleteNode = this.getNode(key);

        if(deleteNode.getParent().getLeft()==deleteNode){
            deleteNode.getParent().setLeft(null);
        }
        else if(deleteNode.getParent().getRight()==deleteNode){
            deleteNode.getParent().setRight(null);
        }
    }

    private void deleteWithOneChild(K key){
        Node<K,V> deleteNode = this.getNode(key);
        Node<K,V> childOfDeleted;

        if(deleteNode.getRight()!=null){
            childOfDeleted= deleteNode.getRight();
        }
        else {
            childOfDeleted=deleteNode.getRight();
        }
        if(deleteNode.getParent().getLeft()==deleteNode){
            deleteNode.getParent().setLeft(childOfDeleted);
        }
        else if(deleteNode.getParent().getRight()==deleteNode){
            deleteNode.getParent().setRight(childOfDeleted);
        }
        deleteNode.setRight(null);
        deleteNode.setLeft(null);
    }

    private void deleteWithTwoChild(K key) {
        Node<K,V> deleteNode = this.getNode(key);
        Node<K,V> nearestToDeleted = deleteNode.findNearest();

       if(nearestToDeleted.getParent().getRight()==nearestToDeleted){
           nearestToDeleted.getParent().setRight(null);
       }
       else if(nearestToDeleted.getParent().getLeft()==nearestToDeleted){
            nearestToDeleted.getParent().setLeft(null);
        }
       deleteNode.setValue(nearestToDeleted.getValue());
       deleteNode.setKey(nearestToDeleted.getKey());
    }

    public Node<K,V> delete(K key) {
        Node<K, V> nodeToReturn = new Node<>(getNode(key).getKey(),getNode(key).getValue());
        Node<K, V> deleteNode = getNode(key);

        if(deleteNode.isLeaf()){
            deleteLeaf(key);
        }
        else if (deleteNode.isHaveOneChild()){
            deleteWithOneChild(key);
        }
        else if(deleteNode.isHaveTwoChild()){
            deleteWithTwoChild(key);
        }
        return nodeToReturn;
    }

    @Override
    public Iterator iterator() {
        return new BinarySerchTreeIterator(this);
    }

    public void reverse() {
        Node<K,V> node = this.root;
        node.swapAllChildrens();
    }

    Node<K, V> getRoot() {
        return root;
    }
    }


