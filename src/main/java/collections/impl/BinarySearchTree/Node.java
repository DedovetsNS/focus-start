package collections.impl.BinarySearchTree;

public class  Node <K extends Comparable,V> {

    private K key;
    private V value;
    private Node<K, V> left = null;
    private Node<K, V> right = null;
    private Node<K, V> parent = null;

    public Node() {
    }

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    boolean isLeaf() {
        if (this.getLeft() == null && this.getRight() == null) {
            return true;
        }
      else{
          return false;
        }
    }

    boolean isHaveOneChild() {
        if ((this.getLeft() == null && this.getRight() != null)||
                (this.getLeft() != null && this.getRight() == null)) {
            return true;
        }
        else{
            return false;
        }
    }

    boolean isHaveTwoChild() {
        if (this.getLeft() != null && this.getRight() != null) {
            return true;
        }
        else{
            return false;
        }
    }

    Node<K,V> findNearest (){
        Node<K,V> mostLeft = this.getRight();
        Node<K,V> mostRight = this.getLeft();

        while (mostLeft.getLeft()!=null){
            mostLeft = mostLeft.getLeft();
        }
        while (mostRight.getRight()!=null){
            mostRight = mostRight.getRight();
        }
        if(Math.abs(mostLeft.getKey().compareTo(this.getKey()))
                >=
                Math.abs(mostRight.getKey().compareTo(this.getKey()))){
            return mostRight;
        }
        else {
            return mostLeft;
        }
    }

    void swapAllChildrens(){
        Node<K,V> node = this;
        if(node==null) {
            return;
        }
        Node<K,V> saveRightNode = node.getRight();
        node.setRight(node.getLeft());
        node.setLeft(saveRightNode);
        if(node.getLeft()!=null) {
            node.getLeft().swapAllChildrens();
        }
        if(node.getRight()!=null) {
            node.getRight().swapAllChildrens();
        }
    }

     K getKey() { return key; }

     void setKey(K key) { this.key = key; }

     V getValue() { return value; }

     void setValue(V value) { this.value = value; }

     Node<K, V> getLeft() { return left; }

     void setLeft(Node<K, V> left) { this.left = left; }

     Node getRight() {
        return right;
    }

     void setRight(Node<K, V> right) { this.right = right; }

     Node<K, V> getParent() {
        return parent;
    }

     void setParent(Node<K, V> parent) {
        this.parent = parent;
    }
}