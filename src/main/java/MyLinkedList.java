public class MyLinkedList {

    private Node head;
    private int size;

    public void add(int e){

        if (head == null) {
            head = new Node(e);
        }else {
            Node last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(new Node(e));
        }
        size++;
    }

    public int get(int index) throws IndexOutOfBoundsException{

        if (index >= size)
            throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i=0; i<index; i++){
            current = current.getNext();
        }
        return current.getValue();
    }

    public void remove(int index){

        if (index >= size)
            throw new IndexOutOfBoundsException();
        Node temp = head;
        Node previous = null;
        if (index == 0){
            head = head.getNext();
            size--;
        }else {
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    previous.setNext(temp.getNext());
                    size--;
                } else {
                    previous = temp;
                    temp = temp.getNext();
                }
            }
        }
    }

    public void remove2(int index){
        if (index == 0){
            head = head.getNext();
            size--;
            return;
        }

        int currentIndex = 0;
        Node temp = head;

        while (temp != null){
            if(currentIndex == index -1){
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            }else {
                temp = temp.getNext();
                currentIndex++;
            }
        }
    }

    public String toString(){

        Node temp = head;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++){
            sb.append("[");
            sb.append(temp.getValue());
            sb.append("]");
            temp = temp.getNext();
        }
        return sb.toString();
    }

    private static class Node{

        private Node next;
        private int value;

        Node(int value){
            this.value = value;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }

    public int getSize() {
        return size;
    }
}
