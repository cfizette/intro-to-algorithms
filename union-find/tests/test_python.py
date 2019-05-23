from src.union_find import *
import pdb

def union_find_tester(union_find_class):
    # Desired connected components:
    # {0,5,6}, {1,2,7}, {8,3,4,9}
    # N = 10
    with open('connections.txt', 'r') as f:
        lines = []
        for line in f:
            lines.append(line.split(' '))

    union_find = union_find_class(N=10)
    for p, q in lines:
        union_find.union(int(p),int(q))

    #pdb.set_trace()

    assert union_find.connected(0,6)
    assert union_find.connected(5,6)
    assert union_find.connected(0,5)

    assert union_find.connected(1,2)
    assert union_find.connected(2,7)
    assert union_find.connected(1,7)

    assert union_find.connected(8,3)
    assert union_find.connected(8,4)
    assert union_find.connected(8,9)
    assert union_find.connected(3,4)
    assert union_find.connected(3,9)
    assert union_find.connected(4,9)

    assert not union_find.connected(6,1)
    assert not union_find.connected(2,4)


def test_quick_find():
    union_find_tester(QuickFind)


def test_quick_union():
    union_find_tester(QuickUnion)


def test_optimized_quick_union():
    union_find_tester(OptimizedUnionFind)