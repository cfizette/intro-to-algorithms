/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item item){
            this.item = item;
        }
    }

    public Deque() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node curr = head;

        @Override
        public boolean hasNext() {
            return !curr.next.equals(tail);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items");
            }
            curr = curr.next;
            return curr.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove unsupported");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        Node newNode = new Node(item);
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        Node newNode = new Node(item);
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node tmp = head.next; //the node to be returned
        head.next = tmp.next;
        tmp.next.prev = head;
        size--;
        return tmp.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node tmp = tail.prev;
        tail.prev = tmp.prev;
        tmp.prev.next = tail;
        size--;
        return tmp.item;
    }

    public static void main(String[] args) {

    }
}
