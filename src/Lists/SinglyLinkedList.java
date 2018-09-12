package Lists;

//TODO: abstract this to accept all object types not just ints.
class SinglyLinkedList {

    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Inserts something at the head
    public void insertHead(int x){
        Node newNode = new Node(x);
        newNode.next = this.head;
        this.head = newNode;
    }

    public boolean isEmpty(){
        return(this.head == null);
    }

    //TODO fully implement
    public Node getNth(int index){
        Node currentNode = this.head;
        while (--index > 0){
            currentNode = currentNode.next;
        }
        return(currentNode);
    }

    //TODO fully implement
    public void insertNth(int data, int index){
        if (index == 0){
            this.insertHead(data);
        }

        else{

        }
    }

    //Keep track of number of elements added to list
    private void incrementSize(){
        this.size ++;
    }
}


class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
