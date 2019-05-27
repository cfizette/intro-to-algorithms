
class BaseStack:
    def push(self, x):
        pass

    def pop(self):
        pass

    def is_empty(self):
        pass

    def size(self):
        pass


class Node:
    def __init__(self, x):
        self.val = x
        self.next = None


# We could implement LinkedList separately but not right now
class LinkedListStack(BaseStack):
    def __init__(self):
        self.first = None

    def push(self, x):
        new_first = Node(x)
        if self.first:
            old_first = self.first
            new_first.next = old_first
        self.first = new_first

    def pop(self):
        old_first = self.first
        self.first = old_first.next
        return old_first

    def is_empty(self):
        return self.first is None

    def size(self):
        n = 0
        current = self.first
        while current:
            current = current.next
            n += 1
        return n


# Python will handle array resizing for us, a little boring
class ArrayStack(BaseStack):
    def __init__(self):
        self.n = 0
        self.stack = []

    def push(self, x):
        self.stack[self.n] = x
        self.n += 1

    def pop(self):
        if not self.is_empty():
            tmp = self.stack[self.n-1]
            self.n -= 1
            return tmp
        raise IndexError("Can't pop from empty stack")

    def is_empty(self):
        return self.n == 0

    def size(self):
        return self.n

# TODO: implement queues and add unit tests, too boring to do right now
