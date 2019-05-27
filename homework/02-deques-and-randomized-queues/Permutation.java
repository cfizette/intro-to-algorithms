/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        int k = Integer.valueOf(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randQueue.enqueue(item);
        }
        while (k > 0) {
            StdOut.println(randQueue.dequeue());
            k--;
        }
    }
}
