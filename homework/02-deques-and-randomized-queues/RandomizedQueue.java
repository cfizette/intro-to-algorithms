/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int lastIndex;

    public RandomizedQueue() {
        // @SuppressWarnings("unchecked")
        Item[] a = (Item[]) new Object[1];
        array = a;
        lastIndex = -1; // index of last added item, since nothing was added initialize to -1
    }

    public int size() {
        return lastIndex + 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int newSize) {
        // @SuppressWarnings("unchecked")
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i <= lastIndex; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null values");
        }
        if (lastIndex + 1 >= array.length) {
            resize(array.length * 2);
        }
        lastIndex++;
        array[lastIndex] = item;
    }

    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniform(lastIndex + 1);
        Item removed = array[i];
        array[i] = array[lastIndex];
        array[lastIndex] = null; // free up memory
        lastIndex--;
        if (size() > 0 && size() <= array.length / 4) {
            resize(array.length / 2);
        }
        return removed;
    }

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniform(lastIndex + 1);
        return array[i];
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private Item[] copiedArray;
        private int copiedLastIndex;

        RandomizedIterator() {
            // copy contents of array
            // @SuppressWarnings("unchecked")
            Item[] a = (Item[]) new Object[lastIndex + 1];
            for (int i = 0; i <= lastIndex; i++) {
                a[i] = array[i];
            }
            copiedArray = a;
            copiedLastIndex = lastIndex;
        }

        @Override
        public boolean hasNext() {
            return copiedLastIndex >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items");
            }
            int i = StdRandom.uniform(copiedLastIndex + 1);
            Item removed = copiedArray[i];
            copiedArray[i] = copiedArray[copiedLastIndex];
            copiedLastIndex--;
            return removed;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}
