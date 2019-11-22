package collections.impl.LinkedList;

    class Node<E> {
    private Node next;
    private Node previos;
    private E value;


         Node getNext() {
            return next;
        }

         void setNext(Node next) {
             this.next = next;
        }

         Node getPrevios() {
             return previos;
        }

         void setPrevios(Node previos) {
            this.previos = previos;
        }

         E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

     Node(E value) {
        this.value = value;
    }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
